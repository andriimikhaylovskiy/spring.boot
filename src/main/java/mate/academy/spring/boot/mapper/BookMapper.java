package mate.academy.spring.boot.mapper;

import mate.academy.spring.boot.config.MapperConfig;
import mate.academy.spring.boot.dto.book.BookDto;
import mate.academy.spring.boot.dto.book.CreateBookRequestDto;
import mate.academy.spring.boot.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {

    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);

    Book updateBookFromDto(@MappingTarget Book book, CreateBookRequestDto requestDto);
}
