package mk.ukim.finki.bookshop.service.domain.impl;

import mk.ukim.finki.bookshop.exception.NoMostRentedBookAuthorFoundException;
import mk.ukim.finki.bookshop.exception.NoBookRentsFoundException;
import mk.ukim.finki.bookshop.exception.NoMostRentedBookFoundException;
import mk.ukim.finki.bookshop.exception.NoUserWithMostRentedBooksFoundException;
import mk.ukim.finki.bookshop.model.domain.Author;
import mk.ukim.finki.bookshop.model.domain.User;
import mk.ukim.finki.bookshop.model.domain.book.Book;
import mk.ukim.finki.bookshop.model.domain.book.BookRent;
import mk.ukim.finki.bookshop.repository.BookRentRepository;
import mk.ukim.finki.bookshop.service.domain.BookRentService;
import mk.ukim.finki.bookshop.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookRentServiceImpl implements BookRentService {
    private final UserService userService;
    private final BookRentRepository bookRentRepository;

    public BookRentServiceImpl(UserService userService, BookRentRepository bookRentRepository) {
        this.userService = userService;
        this.bookRentRepository = bookRentRepository;
    }


    @Override
    public List<Book> findAllRentedBooksForCurrentUser() {
        User authUser = userService.getAuthenticatedUser();
        List<Book> borrowedBooks = bookRentRepository.findByUser(authUser)
                .stream().map(BookRent::getBook).toList();

        if(borrowedBooks.isEmpty()) {
            throw new NoBookRentsFoundException();
        }

        return borrowedBooks;
    }

    @Override
    public Book findMostRentedBook() {
        List<BookRent> bookRents = bookRentRepository.findAll();

        Map<Book, Long> bookCountMap = bookRents.stream()
                .collect(Collectors.groupingBy(
                        BookRent::getBook,
                        Collectors.counting()
                ));

        return bookCountMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow(NoMostRentedBookFoundException::new);
    }

    @Override
    public User findUserWithMostRentedBooks() {
        List<BookRent> bookRents = bookRentRepository.findAll();

        Map<User, Long> userBookRentCountMap = bookRents.stream()
                .collect(Collectors.groupingBy(
                        BookRent::getUser,
                        Collectors.counting()
                ));

        return userBookRentCountMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow(NoUserWithMostRentedBooksFoundException::new);
    }

    @Override
    public Author findMostRentedBookAuthor() {
        List<BookRent> bookRents = bookRentRepository.findAll();

        Map<Author, Long> authorBookRentCountMap = bookRents.stream()
                .collect(Collectors.groupingBy(
                        bookRent -> bookRent.getBook().getAuthor(),
                        Collectors.counting()
                ));

        return authorBookRentCountMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow(NoMostRentedBookAuthorFoundException::new);
    }
}
