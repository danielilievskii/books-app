package mk.ukim.finki.bookshop.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoMostRentedBookAuthorFoundException extends RuntimeException {

    public NoMostRentedBookAuthorFoundException() {
        super(String.format("No most rented book author found."));
    }
}
