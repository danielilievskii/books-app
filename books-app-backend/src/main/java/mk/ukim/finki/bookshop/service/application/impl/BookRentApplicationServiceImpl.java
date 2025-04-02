package mk.ukim.finki.bookshop.service.application.impl;

import mk.ukim.finki.bookshop.dto.DisplayAuthorDto;
import mk.ukim.finki.bookshop.dto.DisplayBookDto;
import mk.ukim.finki.bookshop.dto.DisplayUserDto;
import mk.ukim.finki.bookshop.service.application.BookRentApplicationService;
import mk.ukim.finki.bookshop.service.domain.BookRentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookRentApplicationServiceImpl implements BookRentApplicationService {
    private final BookRentService bookRentService;

    public BookRentApplicationServiceImpl(BookRentService bookRentService) {
        this.bookRentService = bookRentService;
    }

    @Override
    public List<DisplayBookDto> findAllRentedBooksForCurrentUser() {
        return DisplayBookDto.from(bookRentService.findAllRentedBooksForCurrentUser());
    }

    @Override
    public Optional<DisplayBookDto> findMostRentedBook() {
        return Optional.of(DisplayBookDto.from(bookRentService.findMostRentedBook()));
    }

    @Override
    public Optional<DisplayUserDto> findUserWithMostRentedBooks() {
        return Optional.of(DisplayUserDto.from(bookRentService.findUserWithMostRentedBooks()));
    }

    @Override
    public Optional<DisplayAuthorDto> findMostRentedBookAuthor() {
        return Optional.of(DisplayAuthorDto.from(bookRentService.findMostRentedBookAuthor()));
    }
}
