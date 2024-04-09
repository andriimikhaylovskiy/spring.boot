package mate.academy.spring.boot.repository.book;

import java.util.Arrays;

import mate.academy.spring.boot.model.Book;
import mate.academy.spring.boot.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CoverImageSpecificationProvider implements SpecificationProvider<Book> {
    @Override
    public String getKey() {
        return "coverImage";
    }

    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> root.get("coverImage").in(Arrays.stream(params).toArray());
    }
}
