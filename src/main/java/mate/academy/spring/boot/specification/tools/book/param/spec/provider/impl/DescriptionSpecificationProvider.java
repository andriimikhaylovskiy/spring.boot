package mate.academy.spring.boot.specification.tools.book.param.spec.provider.impl;

import java.util.Arrays;
import mate.academy.spring.boot.model.Book;
import mate.academy.spring.boot.specification.tools.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class DescriptionSpecificationProvider implements SpecificationProvider<Book> {

    private static final String DESCRIPTION_FIELD = "description";

    @Override
    public String getKey() {
        return DESCRIPTION_FIELD;
    }

    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> root
                .get(DESCRIPTION_FIELD).in(Arrays.asList(params));
    }
}

