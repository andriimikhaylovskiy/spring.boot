package mate.academy.spring.boot.mapper;

import mate.academy.spring.boot.dto.BookDto;
import mate.academy.spring.boot.dto.CreateBookRequestDto;
import mate.academy.spring.boot.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {
    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "id", ignore = true)
    Book createBookRequestDtoToBook(CreateBookRequestDto dto);

    BookDto toDto(Book book);
}
