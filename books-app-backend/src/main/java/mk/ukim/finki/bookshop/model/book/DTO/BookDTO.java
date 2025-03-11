package mk.ukim.finki.bookshop.model.book.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import mk.ukim.finki.bookshop.model.Author;
import mk.ukim.finki.bookshop.model.Category;
import mk.ukim.finki.bookshop.model.book.Book;
import mk.ukim.finki.bookshop.repository.AuthorRepository;

import java.util.Optional;

@Data
@AllArgsConstructor
public class BookDTO {

    private String name;

    private Category category;

    private Long author;

    private Integer availableCopies;

    public BookDTO() {}

}
