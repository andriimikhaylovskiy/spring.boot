package mate.academy.spring.boot.dto.shoppingcart;

import java.util.Set;
import mate.academy.spring.boot.dto.cartitem.CartItemDto;

public record ShoppingCartDto(Long userId,
                              Set<CartItemDto> cartItems) {}
