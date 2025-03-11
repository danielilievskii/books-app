package mk.ukim.finki.bookshop.service.impl;

import mk.ukim.finki.bookshop.model.Author;
import mk.ukim.finki.bookshop.model.book.Book;
import mk.ukim.finki.bookshop.model.book.BookConverter;
import mk.ukim.finki.bookshop.model.book.BookCopy;
import mk.ukim.finki.bookshop.model.book.DTO.BookDTO;
import mk.ukim.finki.bookshop.repository.AuthorRepository;
import mk.ukim.finki.bookshop.repository.BookRepository;
import mk.ukim.finki.bookshop.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    private final BookConverter bookConverter;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, BookConverter bookConverter) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.bookConverter = bookConverter;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> add(BookDTO bookDTO) {
        return Optional.of(bookRepository.save(bookConverter.toEntity(bookDTO)));
    }

    @Override
    public Optional<Book> update(Long id, BookDTO bookDTO) {
        Book targetBook = bookRepository.findById(id).orElse(null);
        Author author = authorRepository.findById(bookDTO.getAuthor()).orElse(null);

        if(targetBook != null) {
            targetBook.setName(bookDTO.getName());
            targetBook.setAuthor(author);
            targetBook.setCategory(bookDTO.getCategory());

//            int oldCopies = targetBook.getAvailableCopies();
//            int newCopies = bookDTO.getAvailableCopies();

//            if(newCopies > oldCopies) {
//                for (int i = 0; i < newCopies - oldCopies; i++) {
//                    targetBook.getCopies().add(new BookCopy(targetBook));
//                }
//            } else if (newCopies < oldCopies) {
//                List<BookCopy> copies = targetBook.getCopies();
//                int toRemove = oldCopies - newCopies;
//                for (int i = 0; i < toRemove; i++) {
//                    copies.removeLast();
//                }
//            }

            targetBook.setAvailableCopies(bookDTO.getAvailableCopies());
            return Optional.of(bookRepository.save(targetBook));
        }

        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);

    }

    @Override
    public boolean borrowById(Long id) {
        return bookRepository.findById(id)
                .filter(book -> book.getAvailableCopies() > 0)
                .map(book -> {
                    book.setAvailableCopies(book.getAvailableCopies() - 1);
                    bookRepository.save(book);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public boolean returnById(Long id) {
       return bookRepository.findById(id)
               .map(book -> {
                   book.setAvailableCopies(book.getAvailableCopies() + 1);
                   bookRepository.save(book);
                   return true;
               })
               .orElse(false);
    }
}
