package mate.academy.spring.boot.dto;

public record BookSearchParametersDto(String[] titles,
                                      String[] authors,
                                      String[] isbns,
                                      String[] prices,
                                      String[] coverImages,
                                      String[] descriptions) {
}
