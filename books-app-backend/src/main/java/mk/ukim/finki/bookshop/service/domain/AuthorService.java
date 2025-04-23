package mk.ukim.finki.bookshop.service.domain;

import mk.ukim.finki.bookshop.model.domain.Author;
import mk.ukim.finki.bookshop.model.projections.AuthorProjection;
import mk.ukim.finki.bookshop.model.views.AuthorsPerCountryView;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();
    List<AuthorProjection> findAllAuthorsNames();
    Author findById(Long id);

    Author add(Author author);
    Author update(Long id, Author author);
    void deleteById(Long id);

    List<AuthorsPerCountryView> getAuthorsPerCountry();
    void refreshAuthorsPerCountryView();


}
