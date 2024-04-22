package mate.academy.spring.boot.service;

import java.util.List;
import mate.academy.spring.boot.dto.BookDto;
import mate.academy.spring.boot.dto.BookSearchParametersDto;
import mate.academy.spring.boot.dto.CreateBookRequestDto;
import mate.academy.spring.boot.model.Book;
import org.springframework.data.domain.Pageable;

public interface BookService {
    Book save(Book book);

    List<BookDto> findAll(Pageable pageable);

    BookDto getBookById(Long id);

    BookDto createBook(CreateBookRequestDto bookDto);

    void deleteById(Long id);

    List<BookDto> search(BookSearchParametersDto searchParameters);
}
