package mate.academy.spring.boot.repository.book;

import java.util.Arrays;
import mate.academy.spring.boot.model.Book;
import mate.academy.spring.boot.repository.SpecificationProvider;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class PriceSpecificationProvider implements SpecificationProvider<Book> {

    private static final String PRICE_FIELD = "price";

    @Override
    public String getKey() {
        return PRICE_FIELD;
    }

    public Specification<Book> getSpecification(String[] params) {
        return (root, query, criteriaBuilder) -> root
                .get(PRICE_FIELD).in(Arrays.asList(params));
    }
}

