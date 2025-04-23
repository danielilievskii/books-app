package mk.ukim.finki.bookshop.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoBookRentsFoundException extends RuntimeException {

    public NoBookRentsFoundException() {
        super(String.format("No rented books"));
    }
}
