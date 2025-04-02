package mk.ukim.finki.bookshop.service.domain.impl;

import mk.ukim.finki.bookshop.exception.*;
import mk.ukim.finki.bookshop.model.domain.Author;
import mk.ukim.finki.bookshop.model.domain.User;
import mk.ukim.finki.bookshop.model.domain.book.BookRent;
import mk.ukim.finki.bookshop.model.domain.book.Book;
import mk.ukim.finki.bookshop.repository.AuthorRepository;
import mk.ukim.finki.bookshop.repository.BookRepository;
import mk.ukim.finki.bookshop.repository.BookRentRepository;
import mk.ukim.finki.bookshop.repository.UserRepository;
import mk.ukim.finki.bookshop.service.domain.BookService;
import mk.ukim.finki.bookshop.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final BookRentRepository bookRentRepository;


    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, UserService userService, UserRepository userRepository, BookRentRepository bookRentRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.userService = userService;
        this.userRepository = userRepository;
        this.bookRentRepository = bookRentRepository;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    public Book add(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book update(Long id, Book book) {
        Book targetBook = findById(id);

        targetBook.setName(book.getName());
        targetBook.setAuthor(book.getAuthor());
        targetBook.setCategory(book.getCategory());

//            int oldCopies = targetBook.getAvailableCopies();
//            int newCopies = book.getAvailableCopies();

//            if(newCopies > oldCopies) {
//                for (int i = 0; i < newCopies - oldCopies; i++) {
//                    targetBook.getCopies().add(new BookCopy(targetBook));
//                }
//            } else if (newCopies < oldCopies) {
//                List<BookCopy> copies = targetBook.getCopies();
//                int toRemove = oldCopies - newCopies;
//                for (int i = 0; i < toRemove; i++) {
//                    copies.removeLast();
//                }
//            }

        targetBook.setAvailableCopies(book.getAvailableCopies());
        return bookRepository.save(targetBook);
    }

    @Override
    public void deleteById(Long id) {
        Book targetBook = findById(id);
        bookRepository.delete(targetBook);

    }

    @Override
    public boolean rentById(Long id) {
        return bookRepository.findById(id)
                .filter(book -> book.getAvailableCopies() > 0)
                .map(book -> {
                    //TODO: check if the book is not rented, otherwise throw exception

                    book.setAvailableCopies(book.getAvailableCopies() - 1);
                    bookRepository.save(book);

                    User authUser = userService.getAuthenticatedUser();
                    BookRent bookRent = new BookRent(authUser, book);
                    bookRentRepository.save(bookRent);

                    return true;
                })
                .orElseThrow(() -> new BookOutOfStockException(id));
    }

    @Override
    public boolean returnById(Long id) {
        return bookRepository.findById(id)
                .map(book -> {
                    //TODO: check if the book is rented, otherwise throw exception

                    book.setAvailableCopies(book.getAvailableCopies() + 1);
                    bookRepository.save(book);
                    return true;
                })
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    public void addToWishlist(Long id) {
        User authUser = userService.getAuthenticatedUser();

        for (Book book : authUser.getWishlist()) {
            if (book.getId().equals(id)) {
                throw new BookAlreadyWishlistedException(id);
            }
        }

        Book book = findById(id);
        if(!areBooksAvailable(List.of(book))) {
            throw new BookOutOfStockException(id);
        }

        authUser.getWishlist().add(book);
        userRepository.save(authUser);
    }

    @Override
    public void removeFromWishlist(Long id) {
        User authUser = userService.getAuthenticatedUser();

        Iterator<Book> iterator = authUser.getWishlist().iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getId().equals(id)) {
                iterator.remove();
                userRepository.save(authUser);
                return;
            }
        }

        throw new BookNotWishlistedException(id);
    }

    @Override
    public void rentAllFromWishlist() {
        User authUser = userService.getAuthenticatedUser();

        if(authUser.getWishlist().isEmpty()) {
            throw new EmptyWishlistException();
        }

        if(!areBooksAvailable(authUser.getWishlist())) {
            throw new BooksOutOfStockException();
        }

        for(Book book: authUser.getWishlist()) {
           rentById(book.getId());
        }
    }

    @Override
    public List<Book> findAllFromWishlist() {
        User authUser = userService.getAuthenticatedUser();
        return authUser.getWishlist();
    }


    public boolean areBooksAvailable(List<Book> books) {
        for(Book book: books) {
            if (book.getAvailableCopies() < 1) {
                return false;
            }
        }
        return true;
    }


}
