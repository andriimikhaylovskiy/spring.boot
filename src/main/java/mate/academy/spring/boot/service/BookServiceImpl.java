package mate.academy.spring.boot.service;

import java.util.List;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import mate.academy.spring.boot.dto.BookDto;
import mate.academy.spring.boot.dto.BookSearchParametersDto;
import mate.academy.spring.boot.dto.CreateBookRequestDto;
import mate.academy.spring.boot.exception.EntityNotFoundException;
import mate.academy.spring.boot.mapper.BookMapper;
import mate.academy.spring.boot.model.Book;
import mate.academy.spring.boot.repository.BookRepository;
import mate.academy.spring.boot.specification.tools.book.spec.builder.impl.BookSpecificationBuilder;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final Random myRandom;
    private final BookSpecificationBuilder bookSpecificationBuilder;

    @Override
    public BookDto save(CreateBookRequestDto requestDto) {
        Book book = bookMapper.toModel(requestDto);
        if (book.getIsbn() == null || book.getIsbn().isBlank()) {
            book.setIsbn(generateUniqueIsbn());
        }
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public BookDto getBookById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Book with id: %s not found", id)));
    }

    @Override
    public List<BookDto> getAllByAuthor(String author) {
        return bookRepository.findAllByAuthorContainsIgnoreCase(author).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public List<BookDto> getAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public BookDto updateBook(Long id, CreateBookRequestDto requestDto) {
        Book updateBook = bookMapper.updateBookFromDto(
                bookRepository.findById(id).orElseThrow(() ->
                        new EntityNotFoundException("Can't get book by id = " + id)), requestDto);
        return bookMapper.toDto(updateBook);
    }

    @Override
    public List<BookDto> search(BookSearchParametersDto params, Pageable pageable) {
        Specification<Book> bookSpecification = bookSpecificationBuilder.build(params);
        return bookRepository.findAll(bookSpecification, pageable)
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public List<BookDto> getAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable)
                .stream()
                .map(bookMapper::toDto)
                .toList();
    }

    private String generateUniqueIsbn() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 13; i++) {
            result.append(myRandom.nextInt(10));
        }
        return result.toString();
    }
}
