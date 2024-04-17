package mate.academy.spring.boot.service;

import java.awt.print.Pageable;
import java.util.List;
import mate.academy.spring.boot.dto.BookDto;
import mate.academy.spring.boot.dto.BookSearchParametersDto;
import mate.academy.spring.boot.dto.CreateBookRequestDto;
import mate.academy.spring.boot.model.Book;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();

    List<BookDto> getAll(Pageable pageable);

    List<BookDto> getAll(org.springframework.data.domain.Pageable pageable);

    BookDto getBookById(Long id);

    BookDto createBook(CreateBookRequestDto bookDto);

    void deleteById(Long id);

    List<BookDto> search(BookSearchParametersDto searchParameters);
}
