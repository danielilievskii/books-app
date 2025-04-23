package mk.ukim.finki.bookshop.service.application;

import mk.ukim.finki.bookshop.dto.CreateBookDto;
import mk.ukim.finki.bookshop.dto.DisplayAuthorDto;
import mk.ukim.finki.bookshop.dto.DisplayBookDto;
import mk.ukim.finki.bookshop.dto.DisplayUserDto;
import mk.ukim.finki.bookshop.model.views.BooksPerAuthorView;

import java.util.List;
import java.util.Optional;

public interface BookApplicationService {

    List<DisplayBookDto> findAll();
    DisplayBookDto findById(Long id);

    DisplayBookDto add(CreateBookDto createBookDto);
    DisplayBookDto update(Long id, CreateBookDto createBookDto);
    void deleteById(Long id);

    boolean rentById(Long id);
    boolean returnById(Long id);

    void addToWishlist(Long id);
    void removeFromWishlist(Long id);
    void rentAllFromWishlist();
    List<DisplayBookDto> findAllFromWishlist();

    List<BooksPerAuthorView> getBooksPerAuthorView();

}
