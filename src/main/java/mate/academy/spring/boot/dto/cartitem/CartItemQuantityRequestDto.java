package mate.academy.spring.boot.dto.cartitem;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CartItemQuantityRequestDto(
        @NotNull @Positive int quantity) {
}
