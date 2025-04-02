package mk.ukim.finki.bookshop.service.application;

import mk.ukim.finki.bookshop.dto.CreateBookDto;
import mk.ukim.finki.bookshop.dto.DisplayBookDto;
import mk.ukim.finki.bookshop.model.domain.book.Book;

import java.util.List;

public interface BookApplicationService {

    List<DisplayBookDto> findAll();
    DisplayBookDto findById(Long id);

    DisplayBookDto add(CreateBookDto createBookDto);
    DisplayBookDto update(Long id, CreateBookDto createBookDto);
    void deleteById(Long id);

    boolean borrowById(Long id);
    boolean returnById(Long id);

    void addToWishlist(Long id);
    void removeFromWishlist(Long id);
    void borrowAllFromWishlist();
    List<DisplayBookDto> findAllFromWishlist();
}
