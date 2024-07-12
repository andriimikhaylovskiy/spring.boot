package mate.academy.spring.boot.dto.cartitem;

public record CartItemDto(Long id,
                          Long bookId,
                          String bookTitle,
                          int quantity) {}
