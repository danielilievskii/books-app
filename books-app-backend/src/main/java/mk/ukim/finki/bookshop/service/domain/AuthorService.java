package mk.ukim.finki.bookshop.service.domain;

import mk.ukim.finki.bookshop.model.domain.Author;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();
    Author findById(Long id);

    Author add(Author author);
    Author update(Long id, Author author);
    void deleteById(Long id);
}
