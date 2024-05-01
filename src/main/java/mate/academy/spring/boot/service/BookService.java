package mate.academy.spring.boot.service;

import java.util.List;
import mate.academy.spring.boot.dto.BookDto;
import mate.academy.spring.boot.dto.BookSearchParametersDto;
import mate.academy.spring.boot.dto.CreateBookRequestDto;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    List<BookDto> findAll(Pageable pageable);

    List<BookDto> getAllBooks();

    BookDto getBookById(Long id);

    BookDto createBook(CreateBookRequestDto bookDto);

    void deleteById(Long id);

    List<BookDto> search(BookSearchParametersDto params, Pageable pageable);

}
