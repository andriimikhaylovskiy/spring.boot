package mate.academy.spring.boot.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import mate.academy.spring.boot.dto.BookDto;
import mate.academy.spring.boot.dto.BookSearchParametres;
import mate.academy.spring.boot.dto.CreateBookRequestDto;
import mate.academy.spring.boot.model.Book;
import mate.academy.spring.boot.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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
    public List<BookDto> search(BookSearchParametres) {
        return Collection.emptyList();
    }
}
