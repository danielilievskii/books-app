package mk.ukim.finki.bookshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BookAlreadyWishlistedException extends RuntimeException {

    public BookAlreadyWishlistedException(Long id) {
        super(String.format("Book with id: %d is already wishlisted", id));
    }
}
