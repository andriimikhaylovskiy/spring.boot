package mate.academy.spring.boot.mapper;

import mate.academy.spring.boot.config.MapperConfig;
import mate.academy.spring.boot.dto.BookDto;
import mate.academy.spring.boot.dto.CreateBookRequestDto;
import mate.academy.spring.boot.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface BookMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)

    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);

    Book updateBookFromDto(@MappingTarget Book book, CreateBookRequestDto requestDto);
}
