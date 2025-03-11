package mk.ukim.finki.bookshop.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.bookshop.model.book.Book;
import mk.ukim.finki.bookshop.model.book.DTO.BookDTO;
import mk.ukim.finki.bookshop.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Books", description = "Book management API for librarians")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @Operation(summary = "List all books")
    public ResponseEntity<?> listAllBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll());
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new book", description = "Adds a book with details provided in the request body")
    public ResponseEntity<?> addBook(@RequestBody BookDTO bookDTO) {
        return bookService.add(bookDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Edit a book", description = "Edits an existing book's details")
    public ResponseEntity<?> editBook(@RequestBody BookDTO bookDTO, @PathVariable Long id) {
        if(bookService.findById(id).isPresent()) {
            return bookService.update(id, bookDTO)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a book", description = "Deletes a book by its ID")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        if(bookService.findById(id).isPresent()) {
            bookService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/borrow/{id}")
    @Operation(summary = "Borrow a book", description = "Decreases available copies count by 1")
    public ResponseEntity<?> borrowBook(@PathVariable Long id) {
        if(bookService.findById(id).isPresent()) {
            boolean borrowed = bookService.borrowById(id);
            if(borrowed) {
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                ResponseEntity.status(HttpStatus.CONFLICT).body("No available copies to borrow");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/return/{id}")
    @Operation(summary = "Return a book", description = "Increases available copies count by 1")
    public ResponseEntity<?> returnBook(@PathVariable Long id) {
        if(bookService.findById(id).isPresent()) {
            bookService.returnById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
