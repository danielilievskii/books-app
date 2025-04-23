package mk.ukim.finki.bookshop.model.exception.handler;


import mk.ukim.finki.bookshop.model.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<String> handleBookNotFoundException(BookNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(AuthorNotFoundException.class)
    public ResponseEntity<String> handleAuthorNotFoundException(AuthorNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<String> handleCountryNotFoundException(CountryNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(BookOutOfStockException.class)
    public ResponseEntity<String> handleBookOutOfStockException(BookOutOfStockException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(BookAlreadyWishlistedException.class)
    public ResponseEntity<String> handleBookAlreadyWishlistedException(BookAlreadyWishlistedException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(BookNotWishlistedException.class)
    public ResponseEntity<String> handleBookNotWishlistedException(BookNotWishlistedException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(NoBookRentsFoundException.class)
    public ResponseEntity<String> handleNoBookRentsFoundException(NoBookRentsFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(EmptyWishlistException.class)
    public ResponseEntity<String> handleEmptyWishlistException(EmptyWishlistException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(BooksOutOfStockException.class)
    public ResponseEntity<String> handleBooksOutOfStockException(BooksOutOfStockException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }


    @ExceptionHandler(NoMostRentedBookFoundException.class)
    public ResponseEntity<String> handleNoBookWithMostBorrowsFoundException(NoMostRentedBookFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(NoUserWithMostRentedBooksFoundException.class)
    public ResponseEntity<String> handleNoUserWithMostBorrowedBooksFoundException(NoUserWithMostRentedBooksFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(NoMostRentedBookAuthorFoundException.class)
    public ResponseEntity<String> NoAuthorWithMostBorrowedBooksFoundException(NoMostRentedBookAuthorFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }




}

