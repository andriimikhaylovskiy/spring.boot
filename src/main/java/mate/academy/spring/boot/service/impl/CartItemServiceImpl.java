package mate.academy.spring.boot.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mate.academy.spring.boot.dto.cartitem.CartItemDto;
import mate.academy.spring.boot.dto.cartitem.CartItemQuantityRequestDto;
import mate.academy.spring.boot.dto.cartitem.CreateCartItemRequestDto;
import mate.academy.spring.boot.mapper.CartItemMapper;
import mate.academy.spring.boot.model.Book;
import mate.academy.spring.boot.model.CartItem;
import mate.academy.spring.boot.model.ShoppingCart;
import mate.academy.spring.boot.model.User;
import mate.academy.spring.boot.repository.BookRepository;
import mate.academy.spring.boot.repository.shoppingcart.CartItemRepository;
import mate.academy.spring.boot.repository.shoppingcart.ShoppingCartRepository;
import mate.academy.spring.boot.service.CartItemService;
import org.springframework.stereotype.Service;

@Transactional
@RequiredArgsConstructor
@Service
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartItemRepo;
    private final CartItemMapper cartItemMapper;
    private final BookRepository bookRepo;
    private final ShoppingCartRepository shopCartRepo;

    @Override
    public CartItemDto save(CreateCartItemRequestDto requestDto, ShoppingCart shopCart) {
        CartItem cartItem = cartItemMapper.toModel(requestDto);
        Book bookFromDB = bookRepo.findById(cartItem.getBook().getId()).orElseThrow(() ->
                new EntityNotFoundException("Can't find book by id=" + requestDto.bookId()));
        cartItem.setBook(bookFromDB);
        cartItem.setShopcart(shopCart);
        CartItem savedCartItem = cartItemRepo.save(cartItem);
        shopCart.setCartItems(cartItemRepo.findAllByShopCartId(shopCart.getId()));
        return cartItemMapper.toDto(savedCartItem);
    }

    @Override
    public CartItemDto updateQuantity(User user, Long cartItemId,
                                      CartItemQuantityRequestDto requestDto) {
        CartItem cartItemFromDB = getCartItemByIdAndUser(cartItemId, user);
        cartItemFromDB.setQuantity(requestDto.quantity());
        return cartItemMapper.toDto(cartItemRepo.save(cartItemFromDB));
    }

    @Override
    public void deleteById(Long cartItemId, User user) {
        cartItemRepo.delete(getCartItemByIdAndUser(cartItemId, user));
    }

    private ShoppingCart getShopCartByUser(User user) {
        return shopCartRepo.findById(user.getId()).orElseThrow(() ->
                new EntityNotFoundException("Can't find shopping cart by id: " + user.getId()));
    }

    private CartItem getCartItemByIdAndUser(Long cartItemId, User user) {
        return cartItemRepo.findByIdAndShoppingCartId(cartItemId, getShopCartByUser(user).getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Can't find cartItem by id = %s for this user", cartItemId)));
    }
}
