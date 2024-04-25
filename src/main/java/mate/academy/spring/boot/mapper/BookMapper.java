package mate.academy.spring.boot.mapper;

import mate.academy.spring.boot.dto.BookDto;
import mate.academy.spring.boot.dto.CreateBookRequestDto;
import mate.academy.spring.boot.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapperConfig.class)
public interface BookMapper {

    @Mapping(target = "id", ignore = true)
    Book createBookRequestDtoToBook(CreateBookRequestDto dto);

    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);
}
