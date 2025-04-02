package mk.ukim.finki.bookshop.model.domain.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class BookCopy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Book book;

    private Boolean isBorrowed;

    public BookCopy() {}

    public BookCopy(Book book) {
        this.book = book;
        this.isBorrowed = false;
    }
}
