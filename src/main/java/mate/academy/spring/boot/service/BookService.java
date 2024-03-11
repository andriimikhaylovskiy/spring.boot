package mate.academy.spring.boot.service;

import java.util.List;
import mate.academy.spring.boot.model.Book;

public interface BookService {
    Book save(Book book);

    List<Book> findAll();
}
