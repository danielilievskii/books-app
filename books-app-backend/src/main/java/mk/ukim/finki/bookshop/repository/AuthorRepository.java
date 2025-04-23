package mk.ukim.finki.bookshop.repository;

import mk.ukim.finki.bookshop.model.domain.Author;
import mk.ukim.finki.bookshop.model.projections.AuthorProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("select a from Author a")
    List<AuthorProjection> takeNameAndSurnameByProjection();
}
