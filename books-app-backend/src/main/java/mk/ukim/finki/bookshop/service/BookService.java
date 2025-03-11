package mk.ukim.finki.bookshop.service;

import mk.ukim.finki.bookshop.model.book.Book;
import mk.ukim.finki.bookshop.model.book.DTO.BookDTO;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();
    Optional<Book> findById(Long id);

    Optional<Book> add(BookDTO bookDTO);
    Optional<Book> update(Long id, BookDTO bookDTO);
    void deleteById(Long id);

    boolean borrowById(Long id);
    boolean returnById(Long id);
}
