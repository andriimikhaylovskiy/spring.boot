package mate.academy.spring.boot.repository;

import mate.academy.spring.boot.dto.BookSearchParametres;
import org.springframework.data.jpa.domain.Specification;

public interface SpecificationBuilder<T> {
    Specification<T> build(BookSearchParametres bookSearchParametres);
}
