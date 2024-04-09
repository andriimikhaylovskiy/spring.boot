package mate.academy.spring.boot.dto;

import java.math.BigDecimal;
public record BookSearchParametres(String[] titles,
                                   String[] authors,
                                   String[] isbns,
                                   String[] prices,
                                   String[] coverImages,
                                   String[] descriptions) {
}
