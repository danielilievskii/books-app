package mk.ukim.finki.bookshop.service.domain.impl;

import mk.ukim.finki.bookshop.events.AuthorCreatedEvent;
import mk.ukim.finki.bookshop.events.AuthorDeletedEvent;
import mk.ukim.finki.bookshop.events.AuthorUpdatedEvent;
import mk.ukim.finki.bookshop.model.exception.AuthorNotFoundException;
import mk.ukim.finki.bookshop.model.domain.Author;
import mk.ukim.finki.bookshop.model.domain.book.Book;
import mk.ukim.finki.bookshop.model.projections.AuthorProjection;
import mk.ukim.finki.bookshop.model.views.AuthorsPerCountryView;
import mk.ukim.finki.bookshop.repository.AuthorRepository;
import mk.ukim.finki.bookshop.repository.AuthorsPerCountryViewRepository;
import mk.ukim.finki.bookshop.repository.BookRepository;
import mk.ukim.finki.bookshop.service.domain.AuthorService;
import mk.ukim.finki.bookshop.service.domain.CountryService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryService countryService;
    private final BookRepository bookRepository;
    private final AuthorsPerCountryViewRepository authorsPerCountryViewRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryService countryService, BookRepository bookRepository, AuthorsPerCountryViewRepository authorsPerCountryViewRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
        this.bookRepository = bookRepository;
        this.authorsPerCountryViewRepository = authorsPerCountryViewRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public List<AuthorProjection> findAllAuthorsNames() {
        return authorRepository.takeNameAndSurnameByProjection();
    }

    @Override
    public Author findById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
    }

    @Override
    public Author add(Author author) {
        Author savedAuthor = authorRepository.save(author);
        applicationEventPublisher.publishEvent(new AuthorCreatedEvent(savedAuthor));
        return savedAuthor;
    }

    @Override
    public Author update(Long id, Author author) {
        Author targetAuthor = findById(id);

        targetAuthor.setName(author.getName());
        targetAuthor.setSurname(author.getName());
        targetAuthor.setCountry(author.getCountry());

        authorRepository.save(targetAuthor);
        applicationEventPublisher.publishEvent(new AuthorUpdatedEvent(targetAuthor));

        return targetAuthor;
    }

    @Override
    public void deleteById(Long id) {
        Author targetAuthor = findById(id);

        for (Book book : targetAuthor.getBookList()) {
            book.setAuthor(null);
            bookRepository.save(book);
        }

        authorRepository.delete(targetAuthor);
        applicationEventPublisher.publishEvent(new AuthorDeletedEvent(targetAuthor));
    }

    @Override
    public List<AuthorsPerCountryView> getAuthorsPerCountry() {
        return authorsPerCountryViewRepository.findAll();
    }

    @Override
    public void refreshAuthorsPerCountryView() {
        this.authorsPerCountryViewRepository.refreshMaterializedView();
    }
}
