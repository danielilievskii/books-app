package mk.ukim.finki.bookshop.model.book;

import jakarta.persistence.*;
import lombok.*;
import mk.ukim.finki.bookshop.model.Author;
import mk.ukim.finki.bookshop.model.Category;
import mk.ukim.finki.bookshop.model.book.DTO.BookDTO;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    private Author author;

    private Integer availableCopies;

//    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
//    private List<BookCopy> copies;

    public Book() {}

    public Book (String name, Category category, Author author, Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.author = author;
        this.availableCopies = availableCopies;

//        this.copies = new ArrayList<>();
//        for(int i=0; i<availableCopies; i++) {
//            this.copies.add(new BookCopy(this));
//        }

    }

}
