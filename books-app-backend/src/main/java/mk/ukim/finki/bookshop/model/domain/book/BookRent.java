package mk.ukim.finki.bookshop.model.domain.book;

import jakarta.persistence.*;
import lombok.Data;
import mk.ukim.finki.bookshop.model.domain.User;

@Entity
@Data
public class BookRent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public BookRent() {}

    public BookRent(User user, Book book) {
        this.book = book;
        this.user = user;
    }


}
