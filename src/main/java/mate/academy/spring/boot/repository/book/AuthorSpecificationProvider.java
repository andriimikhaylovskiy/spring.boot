package mate.academy.spring.boot.repository.book;

import java.util.Arrays;
import mate.academy.spring.boot.model.Book;
import mate.academy.spring.boot.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class AuthorSpecificationProvider implements SpecificationProvider<Book> {

    private static final String AUTHOR_FIELD = "author";

    @Override
    public String getKey() {
        return AUTHOR_FIELD;
    }

    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> root
                .get(AUTHOR_FIELD).in(Arrays.asList(params));
    }
}

