package mk.ukim.finki.bookshop.dto;

import mk.ukim.finki.bookshop.model.domain.User;
import mk.ukim.finki.bookshop.model.domain.book.BookRent;
import mk.ukim.finki.bookshop.model.domain.book.Book;

public record CreateUserBookRental(
        Long userId,
        Long bookId
) {

    public BookRent toUserBookRental(User user, Book book) {
        return new BookRent(user, book);
    }
}
