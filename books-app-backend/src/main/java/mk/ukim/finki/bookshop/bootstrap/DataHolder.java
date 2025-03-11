package mk.ukim.finki.bookshop.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.bookshop.model.Author;
import mk.ukim.finki.bookshop.model.book.Book;
import mk.ukim.finki.bookshop.model.Category;
import mk.ukim.finki.bookshop.model.Country;
import mk.ukim.finki.bookshop.repository.AuthorRepository;
import mk.ukim.finki.bookshop.repository.BookRepository;
import mk.ukim.finki.bookshop.repository.CountryRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Country> countries;
    public static List<Author> authors;
    public static List<Book> books;

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;
    private final BookRepository bookRepository;

    public DataHolder(AuthorRepository authorRepository, CountryRepository countryRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
        this.bookRepository = bookRepository;
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
            authors.add(new Author("J.R.R.", "Tolkien", countries.get(3)));
            authors.add(new Author("Leo", "Tolstoy", countries.get(4)));
            authorRepository.saveAll(authors);
        }

        books = new ArrayList<>();
        if (this.bookRepository.count() == 0) {
            books.add(new Book("Faust", Category.NOVEL, authors.get(0), 5));
            books.add(new Book("Les Misérables", Category.CLASSICS, authors.get(1), 4));
            books.add(new Book("The Adventures of Tom Sawyer", Category.DRAMA, authors.get(2), 6));
//            books.add(new Book("The Lord of the Rings", Category.FANTASY, authors.get(4), 7));
//            books.add(new Book("Animal Farm", Category.BIOGRAPHY, authors.get(3), 4));
//            books.add(new Book("The Hobbit", Category.FANTASY, authors.get(4), 5));
//            books.add(new Book("War and Peace", Category.HISTORY, authors.get(5), 3));
            bookRepository.saveAll(books);
        }
    }

}
