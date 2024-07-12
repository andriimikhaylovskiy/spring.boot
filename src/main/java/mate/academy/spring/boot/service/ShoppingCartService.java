package mate.academy.spring.boot.service;

import mate.academy.spring.boot.dto.cartitem.CartItemQuantityRequestDto;
import mate.academy.spring.boot.dto.cartitem.CreateCartItemRequestDto;
import mate.academy.spring.boot.dto.shoppingcart.ShoppingCartDto;
import mate.academy.spring.boot.model.ShoppingCart;
import mate.academy.spring.boot.model.User;

public interface ShoppingCartService {
    ShoppingCart getShopCart(Long userId);

    ShoppingCartDto getShopCartDto(Long userId);

    void createShoppingCart(User user);

    ShoppingCartDto save(CreateCartItemRequestDto requestDto, ShoppingCart shopCart);

    ShoppingCartDto updateQuantity(User authenticatedUser, Long cartItemId, CartItemQuantityRequestDto requestDto);

    void deleteById(Long cartItemId, User authenticatedUser);

    ShoppingCartDto getShopCartDto(User authenticatedUser);
}
