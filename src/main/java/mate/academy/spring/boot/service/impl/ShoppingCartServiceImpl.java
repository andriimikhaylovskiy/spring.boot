package mate.academy.spring.boot.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mate.academy.spring.boot.dto.cartitem.CartItemQuantityRequestDto;
import mate.academy.spring.boot.dto.cartitem.CreateCartItemRequestDto;
import mate.academy.spring.boot.dto.shoppingcart.ShoppingCartDto;
import mate.academy.spring.boot.mapper.CartItemMapper;
import mate.academy.spring.boot.mapper.ShoppingCartMapper;
import mate.academy.spring.boot.model.Book;
import mate.academy.spring.boot.model.CartItem;
import mate.academy.spring.boot.model.ShoppingCart;
import mate.academy.spring.boot.model.User;
import mate.academy.spring.boot.repository.BookRepository;
import mate.academy.spring.boot.repository.shoppingcart.CartItemRepository;
import mate.academy.spring.boot.repository.shoppingcart.ShoppingCartRepository;
import mate.academy.spring.boot.service.ShoppingCartService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shopCartRepo;
    private final ShoppingCartMapper shopCartMapper;
    private final CartItemRepository cartItemRepo;
    private final CartItemMapper cartItemMapper;
    private final BookRepository bookRepo;

    @Override
    public ShoppingCart getShopCart(Long userId) {
        return shopCartRepo.findById(userId).orElseThrow(() ->
                new EntityNotFoundException("Can't find shopping cart by id:" + userId));
    }

    @Override
    @Transactional
    public ShoppingCartDto getShopCartDto(Long userId) {
        return shopCartMapper.toDto(getShopCart(userId));
    }

    @Override
    public void createShoppingCart(User user) {
        shopCartRepo.save(shopCartMapper.mapUserToShopCart(user));
    }

    @Override
    @Transactional
    public ShoppingCartDto save(CreateCartItemRequestDto requestDto, User authenticatedUser) {
        ShoppingCart shopCart = getShopCart(authenticatedUser.getId());
        CartItem cartItem = cartItemMapper.toModel(requestDto);
        Book bookFromDB = bookRepo.findById(cartItem.getBook().getId()).orElseThrow(() ->
                new EntityNotFoundException("Can't find book by id=" + requestDto.bookId()));
        cartItem.setBook(bookFromDB);
        cartItem.setShopcart(shopCart);
        CartItem savedCartItem = cartItemRepo.save(cartItem);
        shopCart.setCartItems(cartItemRepo.findAllByShopCartId(shopCart.getId()));
        return shopCartMapper.toDto(shopCart);
    }

    @Override
    @Transactional
    public ShoppingCartDto updateQuantity(User user, Long cartItemId,
                                          CartItemQuantityRequestDto requestDto) {
        CartItem cartItemFromDB = getCartItemByIdAndUser(cartItemId, user);
        cartItemFromDB.setQuantity(requestDto.quantity());
        cartItemRepo.save(cartItemFromDB);
        return getShopCartDto(user.getId());
    }

    @Override
    @Transactional
    public void deleteById(Long cartItemId, User user) {
        cartItemRepo.delete(getCartItemByIdAndUser(cartItemId, user));
    }

    /*@Override
    public ShoppingCartDto getShopCartDto(User authenticatedUser) {
        return null;
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
