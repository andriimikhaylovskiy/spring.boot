package mate.academy.spring.boot.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import mate.academy.spring.boot.validation.FieldsValueMatch;
import org.hibernate.validator.constraints.Length;

@FieldsValueMatch(field = "password",
        fieldMatch = "repeatPassword",
        message = "These passwords must match")
public record UserRegistrationRequestDto(
        @NotBlank @Email String email,
        @NotBlank @Length(min = 8, max = 25) String password,
        @NotBlank @Length(min = 8, max = 25) String repeatPassword,
        @NotBlank String firstName,
        @NotBlank String lastName,
        String shippingAddress) {}
