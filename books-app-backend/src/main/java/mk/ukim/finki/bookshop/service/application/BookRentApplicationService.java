package mk.ukim.finki.bookshop.service.application;

import mk.ukim.finki.bookshop.dto.DisplayAuthorDto;
import mk.ukim.finki.bookshop.dto.DisplayBookDto;
import mk.ukim.finki.bookshop.dto.DisplayUserDto;

import java.util.List;
import java.util.Optional;

public interface BookRentApplicationService {
    List<DisplayBookDto> findAllRentedBooksForCurrentUser();
    Optional<DisplayBookDto> findMostRentedBook();
    Optional<DisplayUserDto> findUserWithMostRentedBooks();
    Optional<DisplayAuthorDto> findMostRentedBookAuthor();
}
