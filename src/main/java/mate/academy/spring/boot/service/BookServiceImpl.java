package mate.academy.spring.boot.service;

import java.util.List;
import java.util.stream.Collectors;
import mate.academy.spring.boot.dto.BookDto;
import mate.academy.spring.boot.dto.BookSearchParametersDto;
import mate.academy.spring.boot.dto.CreateBookRequestDto;
import mate.academy.spring.boot.mapper.BookMapper;
import mate.academy.spring.boot.model.Book;
import mate.academy.spring.boot.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<BookDto> getAll() {
        throw new UnsupportedOperationException("Method not implemented");
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
    public List<Object> searchBooks(BookSearchParametersDto searchParameters) {
        List<Book> foundBooks = bookRepository
                .findByTitleAndAuthorAndIsbnAndPriceAndDescriptionAndCoverImageContaining(
                searchParameters.getTitle(),
                searchParameters.getAuthor(),
                searchParameters.getIsbn(),
                searchParameters.getPrice(),
                searchParameters.getDescription(),
                searchParameters.getCoverImage()
        );
        return foundBooks.stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }
}
