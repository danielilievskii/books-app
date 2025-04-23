package mk.ukim.finki.bookshop.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BooksOutOfStockException extends RuntimeException {

    public BooksOutOfStockException() {
        super(String.format("Some books from your wishlist are not in stock right now. Try again later."));
    }
}
