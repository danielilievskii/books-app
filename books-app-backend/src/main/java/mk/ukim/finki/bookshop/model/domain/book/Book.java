package mk.ukim.finki.bookshop.model.domain.book;

import jakarta.persistence.*;
import lombok.*;
import mk.ukim.finki.bookshop.model.domain.Author;
import mk.ukim.finki.bookshop.model.enumeration.Category;

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
    @JoinColumn(nullable = true) // keeping the books after author removal
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
