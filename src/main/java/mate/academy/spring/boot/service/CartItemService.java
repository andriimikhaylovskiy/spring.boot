package mate.academy.spring.boot.service;

import mate.academy.spring.boot.dto.cartitem.CartItemDto;
import mate.academy.spring.boot.dto.cartitem.CartItemQuantityRequestDto;
import mate.academy.spring.boot.dto.cartitem.CreateCartItemRequestDto;
import mate.academy.spring.boot.model.ShoppingCart;
import mate.academy.spring.boot.model.User;

public interface CartItemService {
    CartItemDto save(CreateCartItemRequestDto requestDto, ShoppingCart shopCart);

    CartItemDto updateQuantity(User authenticatedUser, Long cartItemId,
                               CartItemQuantityRequestDto requestDto);

    void deleteById(Long id, User user);
}
