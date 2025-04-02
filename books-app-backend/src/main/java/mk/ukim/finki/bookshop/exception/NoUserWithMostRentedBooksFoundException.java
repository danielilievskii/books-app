package mk.ukim.finki.bookshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoUserWithMostRentedBooksFoundException extends RuntimeException {

    public NoUserWithMostRentedBooksFoundException() {
        super(String.format("No user with most rented books found."));
    }
}
