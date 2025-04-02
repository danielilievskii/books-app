package mk.ukim.finki.bookshop.service.domain.impl;

import mk.ukim.finki.bookshop.exception.AuthorNotFoundException;
import mk.ukim.finki.bookshop.model.domain.Author;
import mk.ukim.finki.bookshop.model.domain.book.Book;
import mk.ukim.finki.bookshop.repository.AuthorRepository;
import mk.ukim.finki.bookshop.repository.BookRepository;
import mk.ukim.finki.bookshop.service.domain.AuthorService;
import mk.ukim.finki.bookshop.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryService countryService;
    private final BookRepository bookRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
    }

    @Override
    public Author add(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author update(Long id, Author author) {
        Author targetAuthor = findById(id);

        targetAuthor.setName(author.getName());
        targetAuthor.setSurname(author.getName());
        targetAuthor.setCountry(author.getCountry());

        return authorRepository.save(targetAuthor);
    }

    @Override
    public void deleteById(Long id) {
        Author targetAuthor = findById(id);

        for (Book book : targetAuthor.getBookList()) {
            book.setAuthor(null);
            bookRepository.save(book);
        }

        authorRepository.delete(targetAuthor);
    }
}
