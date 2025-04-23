package mk.ukim.finki.bookshop.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmptyWishlistException extends RuntimeException {

    public EmptyWishlistException() {
        super(String.format("Book wishlist is empty"));
    }
}
