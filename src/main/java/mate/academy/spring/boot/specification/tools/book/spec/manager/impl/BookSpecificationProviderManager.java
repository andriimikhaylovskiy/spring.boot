package mate.academy.spring.boot.specification.tools.book.spec.manager.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.spring.boot.model.Book;
import mate.academy.spring.boot.specification.tools.SpecificationProvider;
import mate.academy.spring.boot.specification.tools.SpecificationProviderManager;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class BookSpecificationProviderManager implements SpecificationProviderManager<Book> {
    private final List<SpecificationProvider<Book>> bookSpecificationProviders;

    @Override
    public SpecificationProvider<Book> getSpecificationProvider(String key) {
        return bookSpecificationProviders.stream()
                .filter(p -> p.getKey().equals(key))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Can't find correct specification provider for key "
                                + key));
    }
}
