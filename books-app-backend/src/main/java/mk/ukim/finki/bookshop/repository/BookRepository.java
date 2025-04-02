package mk.ukim.finki.bookshop.repository;


import mk.ukim.finki.bookshop.model.domain.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
