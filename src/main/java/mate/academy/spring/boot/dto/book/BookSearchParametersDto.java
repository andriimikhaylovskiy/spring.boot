package mate.academy.spring.boot.dto.book;

public record BookSearchParametersDto(String[] titles,
                                      String[] authors,
                                      String[] isbns,
                                      String[] prices,
                                      String[] coverImages,
                                      String[] descriptions) {
}
