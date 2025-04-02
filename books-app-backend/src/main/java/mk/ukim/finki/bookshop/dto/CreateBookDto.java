package mk.ukim.finki.bookshop.dto;

import mk.ukim.finki.bookshop.model.domain.Author;
import mk.ukim.finki.bookshop.model.enumeration.Category;
import mk.ukim.finki.bookshop.model.domain.book.Book;

public record CreateBookDto(
        String name,
        Category category,
        Long author,
        Integer availableCopies

) {
    public Book toEntity(Author author) {
        return new Book(
                this.name,
                this.category,
                author,
                this.availableCopies
        );
    }
}
