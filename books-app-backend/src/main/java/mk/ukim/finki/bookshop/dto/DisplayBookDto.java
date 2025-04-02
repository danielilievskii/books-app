package mk.ukim.finki.bookshop.dto;

import mk.ukim.finki.bookshop.model.enumeration.Category;
import mk.ukim.finki.bookshop.model.domain.book.Book;

import java.util.List;

public record DisplayBookDto(
        Long id,
        String name,
        Category category,
        DisplayAuthorDto author,
        Integer availableCopies

) {
    public static DisplayBookDto from (Book book) {
        return new DisplayBookDto(
                book.getId(),
                book.getName(),
                book.getCategory(),
                DisplayAuthorDto.from(book.getAuthor()),
                book.getAvailableCopies()
        );
    }
    public static List<DisplayBookDto> from (List<Book> books) {
        return books.stream().map(DisplayBookDto::from).toList();
    }
}
