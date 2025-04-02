package mk.ukim.finki.bookshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookOutOfStockException extends RuntimeException {

    public BookOutOfStockException(Long id) {
        super(String.format("Book with id: %d is out of stock", id));
    }
}
