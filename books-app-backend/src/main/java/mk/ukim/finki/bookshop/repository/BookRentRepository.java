package mk.ukim.finki.bookshop.repository;

import mk.ukim.finki.bookshop.model.domain.User;
import mk.ukim.finki.bookshop.model.domain.book.BookRent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRentRepository extends JpaRepository<BookRent, Long> {
    List<BookRent> findByUser(User user);
}
