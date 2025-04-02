package mk.ukim.finki.bookshop.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.bookshop.model.domain.Author;
import mk.ukim.finki.bookshop.model.domain.User;
import mk.ukim.finki.bookshop.model.domain.book.Book;
import mk.ukim.finki.bookshop.model.enumeration.Category;
import mk.ukim.finki.bookshop.model.domain.Country;
import mk.ukim.finki.bookshop.model.enumeration.Role;
import mk.ukim.finki.bookshop.repository.AuthorRepository;
import mk.ukim.finki.bookshop.repository.BookRepository;
import mk.ukim.finki.bookshop.repository.CountryRepository;
import mk.ukim.finki.bookshop.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Country> countries;
    public static List<Author> authors;
    public static List<Book> books;
    public static List<User> users;

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataHolder(AuthorRepository authorRepository, CountryRepository countryRepository, BookRepository bookRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        countries = new ArrayList<>();
        if (this.countryRepository.count() == 0) {
            countries.add(new Country("Germany", "Europe"));
            countries.add(new Country("France", "Europe"));
            countries.add(new Country("United States", "North America"));
            countries.add(new Country("United Kingdom", "Europe"));
            countries.add(new Country("Russia", "Europe"));
            countryRepository.saveAll(countries);
        }

        authors = new ArrayList<>();
        if (this.authorRepository.count() == 0) {
            authors.add(new Author("Johann", "Goethe", countries.get(0)));
            authors.add(new Author("Victor", "Hugo", countries.get(1)));
            authors.add(new Author("Mark", "Twain", countries.get(2)));
            authors.add(new Author("George", "Orwell", countries.get(3)));
            authorRepository.saveAll(authors);
        }

        books = new ArrayList<>();
        if (this.bookRepository.count() == 0) {
            books.add(new Book("Faust", Category.NOVEL, authors.get(0), 5));
            books.add(new Book("Les Misérables", Category.CLASSICS, authors.get(1), 4));
            books.add(new Book("The Adventures of Tom Sawyer", Category.DRAMA, authors.get(2), 0));
            bookRepository.saveAll(books);
        }

        users = new ArrayList<>();
        if(this.userRepository.count() == 0) {
            users.add(new User("di", passwordEncoder.encode("12345"), "Daniel", Role.ROLE_LIBRARIAN));
            users.add(new User("user",  passwordEncoder.encode("12345"), "User", Role.ROLE_LIBRARIAN));
            userRepository.saveAll(users);
        }
    }

}
