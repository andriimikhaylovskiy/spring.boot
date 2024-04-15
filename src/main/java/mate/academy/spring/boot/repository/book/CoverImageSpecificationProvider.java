package mate.academy.spring.boot.repository.book;

import java.util.Arrays;
import mate.academy.spring.boot.model.Book;
import mate.academy.spring.boot.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CoverImageSpecificationProvider implements SpecificationProvider<Book> {

    private static final String COVER_IMAGE_FIELD = "coverImage";

    @Override
    public String getKey() {
        return COVER_IMAGE_FIELD;
    }

    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> root
                .get(COVER_IMAGE_FIELD).in(Arrays.asList(params));
    }
}

