package mate.academy.spring.boot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.spring.boot.dto.BookDto;
import mate.academy.spring.boot.dto.BookSearchParametersDto;
import mate.academy.spring.boot.dto.CreateBookRequestDto;
import mate.academy.spring.boot.service.BookService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Book management", description = "Endpoints for managing books")
@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
@Validated
public class BookController {
    private final BookService bookService;

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete the book",
            description = "Delete the book by ID")
    public void deleteBookById(@PathVariable @Positive Long id) {
        bookService.deleteById(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book by id",
            description = "Get the book by ID")
    public BookDto getBookById(@PathVariable @Positive Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new book",
            description = "Create a new book entity in the database")
    public BookDto createBook(@RequestBody @Valid CreateBookRequestDto requestDto) {
        return bookService.save(requestDto);
    }

    @GetMapping("/search")
    @Operation(summary = "Search for books",
            description = "Retrieve a list of books based on the provided search parameters.")
    public List<BookDto> searchBooks(BookSearchParametersDto searchParameters,
                                     @ParameterObject @PageableDefault Pageable pageable) {
        return bookService.search(searchParameters, pageable);
    }

    @GetMapping("/books")
    @Operation(summary = "Get all books", description = "Get the all book")
    public List<BookDto> getAll(@ParameterObject @PageableDefault Pageable pageable) {
        return bookService.getAllBooks();
    }

    @GetMapping
    public List<BookDto> findAll(Pageable pageable) {
        return bookService.findAll(pageable);
    }
}
