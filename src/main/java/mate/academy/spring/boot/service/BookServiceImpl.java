package mate.academy.spring.boot.service;

import java.util.Collections;
import java.util.List;
import mate.academy.spring.boot.dto.BookDto;
import mate.academy.spring.boot.dto.BookSearchParametersDto;
import mate.academy.spring.boot.dto.CreateBookRequestDto;
import mate.academy.spring.boot.mapper.BookMapper;
import mate.academy.spring.boot.model.Book;
import mate.academy.spring.boot.repository.BookRepository;
import mate.academy.spring.boot.repository.BookSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecificationBuilder bookSpecificationBuilder;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper,
                           BookSpecificationBuilder bookSpecificationBuilder) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.bookSpecificationBuilder = bookSpecificationBuilder;
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto getBookById(Long id) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public BookDto createBook(CreateBookRequestDto bookDto) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDto> search(BookSearchParametersDto searchParameters) {
        return Collections.emptyList();
    }
}
