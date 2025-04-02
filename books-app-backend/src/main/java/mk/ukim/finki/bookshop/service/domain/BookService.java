package mk.ukim.finki.bookshop.service.domain;
import mk.ukim.finki.bookshop.model.domain.book.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();
    Book findById(Long id);

    Book add(Book book);
    Book update(Long id, Book book);
    void deleteById(Long id);

    boolean borrowById(Long id);
    boolean returnById(Long id);

    void addToWishlist(Long id);
    void removeFromWishlist(Long id);
    void borrowAllFromWishlist();
    List<Book> findAllFromWishlist();
}
