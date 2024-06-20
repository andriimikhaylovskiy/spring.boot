package mate.academy.spring.boot.mapper;

import java.util.Set;
import java.util.stream.Collectors;
import mate.academy.spring.boot.config.MapperConfig;
import mate.academy.spring.boot.dto.book.BookDto;
import mate.academy.spring.boot.dto.book.BookDtoWithoutCategoryIds;
import mate.academy.spring.boot.dto.book.CreateBookRequestDto;
import mate.academy.spring.boot.model.Book;
import mate.academy.spring.boot.model.Category;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    @Mapping(target = "categorySet", ignore = true)
    Book toModel(CreateBookRequestDto requestDto);

    Book updateBookFromDto(@MappingTarget Book book, CreateBookRequestDto requestDto);

    @Mapping(target = "categoryIds", ignore = true)
    BookDto toDto(Book book);

    BookDtoWithoutCategoryIds toDtoWithoutCategories(Book book);

    @AfterMapping
    default void setCategories(@MappingTarget Book book, CreateBookRequestDto requestDto) {
        if (requestDto.categoryIds() == null) {
            book.setCategorySet(Set.of());
            return;
        }
        book.setCategorySet(requestDto.categoryIds().stream()
                .map(Category::new)
                .collect(Collectors.toSet()));
    }

    @AfterMapping
    default void setCategoryIds(@MappingTarget BookDto bookDto, Book book) {
        bookDto.setCategoryIds(book.getCategorySet().stream()
                .map(Category::getId)
                .collect(Collectors.toSet()));
    }
}
