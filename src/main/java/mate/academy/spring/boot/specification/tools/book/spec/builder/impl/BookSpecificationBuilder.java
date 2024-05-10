package mate.academy.spring.boot.specification.tools.book.spec.builder.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.spring.boot.dto.BookSearchParametersDto;
import mate.academy.spring.boot.model.Book;
import mate.academy.spring.boot.specification.tools.SpecificationBuilder;
import mate.academy.spring.boot.specification.tools.SpecificationProviderManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationBuilder implements SpecificationBuilder<Book> {
    private final SpecificationProviderManager<Book> bookSpecificationProviderManager;

    @Override
    public Specification<Book> build(BookSearchParametersDto searchParametres) {
        Specification<Book> spec = Specification.where(null);
        if (searchParametres.titles() != null && searchParametres.titles().length > 0) {
            spec = spec.and(bookSpecificationProviderManager.getSpecificationProvider("title")
                    .getSpecification(searchParametres.titles()));
        }
        if (searchParametres.authors() != null && searchParametres.authors().length > 0) {
            spec = spec.and(bookSpecificationProviderManager.getSpecificationProvider("author")
                    .getSpecification(searchParametres.authors()));
        }
        if (searchParametres.isbns() != null && searchParametres.isbns().length > 0) {
            spec = spec.and(bookSpecificationProviderManager.getSpecificationProvider("isbn")
                    .getSpecification(searchParametres.isbns()));
        }
        if (searchParametres.prices() != null && searchParametres.prices().length > 0) {
            spec = spec.and(bookSpecificationProviderManager.getSpecificationProvider("price")
                    .getSpecification(searchParametres.prices()));
        }
        if (searchParametres.coverImages() != null && searchParametres.coverImages().length > 0) {
            spec = spec.and(bookSpecificationProviderManager.getSpecificationProvider("coverImage")
                    .getSpecification(searchParametres.coverImages()));
        }
        if (searchParametres.descriptions() != null && searchParametres.descriptions().length > 0) {
            spec = spec.and(bookSpecificationProviderManager.getSpecificationProvider("description")
                    .getSpecification(searchParametres.descriptions()));
        }
        return spec;
    }
}
