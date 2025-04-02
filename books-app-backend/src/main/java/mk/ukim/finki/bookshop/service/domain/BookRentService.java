package mk.ukim.finki.bookshop.service.domain;

import mk.ukim.finki.bookshop.model.domain.Author;
import mk.ukim.finki.bookshop.model.domain.User;
import mk.ukim.finki.bookshop.model.domain.book.Book;

import java.util.List;

public interface BookRentService {

    List<Book> findAllRentedBooksForCurrentUser();
    Book findMostRentedBook();
    Author findMostRentedBookAuthor();
    User findUserWithMostRentedBooks();
}
