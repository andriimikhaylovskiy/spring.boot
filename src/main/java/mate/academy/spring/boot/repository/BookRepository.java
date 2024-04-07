package mate.academy.spring.boot.repository;

import java.math.BigDecimal;
import java.util.List;
import mate.academy.spring.boot.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleAndAuthorAndIsbnAndPriceAndDescriptionAndCoverImageContaining(
            String title,
            String author,
            String isbn,
            BigDecimal price,
            String description,
            String coverImage
    );
}
