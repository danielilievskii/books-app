package mk.ukim.finki.bookshop.repository;

import mk.ukim.finki.bookshop.model.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
