package mate.academy.spring.boot.specification.tools.book.param.spec.provider.impl;

import java.util.Arrays;
import mate.academy.spring.boot.model.Book;
import mate.academy.spring.boot.specification.tools.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class IsbnSpecificationProvider implements SpecificationProvider<Book> {

    private static final String ISBN_FIELD = "isbn";

    @Override
    public String getKey() {
        return ISBN_FIELD;
    }

    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> root
                .get(ISBN_FIELD).in(Arrays.asList(params));
    }
}

