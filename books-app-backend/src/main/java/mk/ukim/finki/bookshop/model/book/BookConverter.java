package mk.ukim.finki.bookshop.model.book;

import mk.ukim.finki.bookshop.model.Author;
import mk.ukim.finki.bookshop.model.book.DTO.BookDTO;
import mk.ukim.finki.bookshop.repository.AuthorRepository;
import org.springframework.stereotype.Component;

@Component
public class BookConverter {

    private final AuthorRepository authorRepository;

    public BookConverter(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public BookDTO toBookDTO(Book book) {
        if (book == null) {
            return null;
        }

        BookDTO bookDTO = new BookDTO();
        bookDTO.setName(book.getName());
        bookDTO.setCategory(book.getCategory());
        bookDTO.setAuthor(book.getAuthor().getId());
        bookDTO.setAvailableCopies(book.getAvailableCopies());

        return bookDTO;
    }

    public Book toEntity(BookDTO bookDTO) {
        if (bookDTO == null) {
            return null;
        }

        Author author = authorRepository.findById(bookDTO.getAuthor()).orElse(null);
        return new Book(bookDTO.getName(), bookDTO.getCategory(), author, bookDTO.getAvailableCopies());

    }
}
