package mk.ukim.finki.bookshop.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BookNotWishlistedException extends RuntimeException {

    public BookNotWishlistedException(Long id) {
        super(String.format("Book with id: %d is not wishlisted", id));
    }
}
