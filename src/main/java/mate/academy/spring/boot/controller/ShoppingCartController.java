package mate.academy.spring.boot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import mate.academy.spring.boot.dto.cartitem.CartItemQuantityRequestDto;
import mate.academy.spring.boot.dto.cartitem.CreateCartItemRequestDto;
import mate.academy.spring.boot.dto.shoppingcart.ShoppingCartDto;
import mate.academy.spring.boot.model.User;
import mate.academy.spring.boot.service.ShoppingCartService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Shopping Cart manager", description = "Endpoints for managing user's shopping cart")
@RequiredArgsConstructor
@RestController
@RequestMapping("/cart")
@Validated
public class ShoppingCartController {
    private final ShoppingCartService shopCartService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    @Operation(summary = "Add a some book to the shopping cart",
            description = "Create a new cartItem entity in the database")
    public ShoppingCartDto createCartItem(@RequestBody @Valid CreateCartItemRequestDto requestDto,
                                          Authentication authentication) {
        User authenticatedUser = getAuthenticatedUser(authentication);
        return shopCartService.save(requestDto, authenticatedUser);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    @Operation(summary = "Retrieve authenticated user's shopping cart",
            description = "View owns shopping cart before placing an order")
    public ShoppingCartDto getShopCart(Authentication authentication) {
        return shopCartService.getShopCartDto(getAuthenticatedUser(authentication));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/cart-items/{id}")
    @Operation(summary = "Update the books quantity",
            description = "Update the books quantity in the shopping cart")
    public ShoppingCartDto updateBookQuantity(
            @PathVariable @Positive Long id,
            @RequestBody @Valid CartItemQuantityRequestDto requestDto,
            Authentication authentication) {
        return shopCartService.updateQuantity(getAuthenticatedUser(authentication), id, requestDto);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/cart-items/{id}")
    @Operation(summary = "Remove a book from the shopping cart",
            description = "Remove purchases by id from the shopping cart "
                    + "(physically - not mark it as deleted)")
    public void delete(@PathVariable @Positive Long id, Authentication authentication) {
        shopCartService.deleteById(id, getAuthenticatedUser(authentication));
    }

    private User getAuthenticatedUser(Authentication authentication) {
        return (User) authentication.getPrincipal();
    }
}
