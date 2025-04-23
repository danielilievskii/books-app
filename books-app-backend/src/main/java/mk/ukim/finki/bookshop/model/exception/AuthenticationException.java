package mk.ukim.finki.bookshop.model.exception;

public class AuthenticationException extends RuntimeException {

    public AuthenticationException() {
        super(String.format("Authenticated user not found"));
    }
}
