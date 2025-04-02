package mk.ukim.finki.bookshop.service.application.impl;


import mk.ukim.finki.bookshop.dto.CreateBookDto;
import mk.ukim.finki.bookshop.dto.DisplayAuthorDto;
import mk.ukim.finki.bookshop.dto.DisplayBookDto;
import mk.ukim.finki.bookshop.dto.DisplayUserDto;
import mk.ukim.finki.bookshop.model.domain.Author;
import mk.ukim.finki.bookshop.model.domain.book.Book;
import mk.ukim.finki.bookshop.service.application.BookApplicationService;
import mk.ukim.finki.bookshop.service.domain.AuthorService;
import mk.ukim.finki.bookshop.service.domain.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookApplicationServiceImpl implements BookApplicationService {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookApplicationServiceImpl(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    public List<DisplayBookDto> findAll() {
        return DisplayBookDto.from(bookService.findAll());
    }

    @Override
    public DisplayBookDto findById(Long id) {
        return DisplayBookDto.from(bookService.findById(id));
    }

    @Override
    public DisplayBookDto add(CreateBookDto createBookDto) {
        Author author = authorService.findById(createBookDto.author());
        Book book = createBookDto.toEntity(author);

        return DisplayBookDto.from(bookService.add(book));
    }

    @Override
    public DisplayBookDto update(Long id, CreateBookDto createBookDto) {
        Author author = authorService.findById(createBookDto.author());
        Book book = createBookDto.toEntity(author);

        return DisplayBookDto.from(bookService.update(id, book));
    }

    @Override
    public void deleteById(Long id) {
        bookService.deleteById(id);
    }

    @Override
    public boolean rentById(Long id) {
        return bookService.rentById(id);
    }

    @Override
    public boolean returnById(Long id) {
        return bookService.returnById(id);
    }

    @Override
    public void addToWishlist(Long id) {
        bookService.addToWishlist(id);
    }

    @Override
    public void removeFromWishlist(Long id) {
        bookService.removeFromWishlist(id);
    }

    @Override
    public void rentAllFromWishlist() {
        bookService.rentAllFromWishlist();
    }

    @Override
    public List<DisplayBookDto> findAllFromWishlist() {
        return DisplayBookDto.from(bookService.findAllFromWishlist());
    }

}
