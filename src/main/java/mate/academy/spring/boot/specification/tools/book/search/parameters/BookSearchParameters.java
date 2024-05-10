package mate.academy.spring.boot.specification.tools.book.search.parameters;

import java.util.List;

public record BookSearchParameters(List<String> titles, List<String> authors) {
}
