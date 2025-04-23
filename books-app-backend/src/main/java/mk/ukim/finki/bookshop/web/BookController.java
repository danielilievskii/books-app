package mk.ukim.finki.bookshop.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.bookshop.dto.CreateBookDto;
import mk.ukim.finki.bookshop.service.application.BookApplicationService;
import mk.ukim.finki.bookshop.service.application.BookRentApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Books", description = "Book management API for librarians")
public class BookController {

    private final BookApplicationService bookApplicationService;
    private final BookRentApplicationService bookRentApplicationService;

    public BookController(BookApplicationService bookApplicationService, BookRentApplicationService bookRentApplicationService) {
        this.bookApplicationService = bookApplicationService;
        this.bookRentApplicationService = bookRentApplicationService;
    }

    @GetMapping
    @Operation(summary = "List all books")
    public ResponseEntity<?> listAllBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookApplicationService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "List specific books")
    public ResponseEntity<?> listAllBooks(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookApplicationService.findById(id));
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new book", description = "Adds a book with details provided in the request body")
    public ResponseEntity<?> addBook(@RequestBody CreateBookDto bookDto) {
        return ResponseEntity.ok(bookApplicationService.add(bookDto));
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Edit a book", description = "Edits an existing book's details")
    public ResponseEntity<?> editBook(@RequestBody CreateBookDto bookDto, @PathVariable Long id) {
        return ResponseEntity.ok(bookApplicationService.update(id, bookDto));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a book", description = "Deletes a book by its ID")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        bookApplicationService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/borrow/{id}")
    @Operation(summary = "Borrow a book", description = "Decreases available copies count by 1")
    public ResponseEntity<?> borrowBook(@PathVariable Long id) {
        bookApplicationService.rentById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/return/{id}")
    @Operation(summary = "Return a book", description = "Increases available copies count by 1")
    public ResponseEntity<?> returnBook(@PathVariable Long id) {
        bookApplicationService.returnById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/rents")
    @Operation(summary = "Return borrowed books for current user", description = "")
    public ResponseEntity<?> findAllRentedBooksForCurrentUser() {
        return ResponseEntity.ok(bookRentApplicationService.findAllRentedBooksForCurrentUser());
    }

    @GetMapping("/rents/by-book")
    @Operation(summary = "Returns most borrowedBook", description = "")
    public ResponseEntity<?> findMostRentedBook() {
        return ResponseEntity.ok(bookRentApplicationService.findMostRentedBook());
    }

    @GetMapping("/rents/by-user")
    @Operation(summary = "Returns user with most borrowed books", description = "")
    public ResponseEntity<?> findUserWithMostBorrowedBooks() {
        return ResponseEntity.ok(bookRentApplicationService.findUserWithMostRentedBooks());
    }

    @GetMapping("/rents/by-author")
    @Operation(summary = "Returns author with most borrowed books", description = "")
    public ResponseEntity<?> findMostRentedBookAuthor() {
        return ResponseEntity.ok(bookRentApplicationService.findMostRentedBookAuthor());
    }

    @GetMapping("/wishlist/add/{id}")
    @Operation(summary = "Wishlist a book", description = "Saves book in user's wishlist")
    public ResponseEntity<?> addToWishlist(@PathVariable Long id) {
        bookApplicationService.addToWishlist(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/wishlist/remove/{id}")
    @Operation(summary = "Wishlist a book", description = "Removes book from user's wishlist")
    public ResponseEntity<?> removeFromWishlist(@PathVariable Long id) {
        bookApplicationService.removeFromWishlist(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/wishlist/borrow")
    @Operation(summary = "Borrow all books from wishlist", description = "")
    public ResponseEntity<?> borrowAllFromWishlist() {
        bookApplicationService.rentAllFromWishlist();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/wishlist")
    @Operation(summary = "Fetch book wishlist", description = "Returns user's wishlist")
    public ResponseEntity<?> findAllFromWishlist() {
        return ResponseEntity.ok(bookApplicationService.findAllFromWishlist());
    }

    @GetMapping("/by-author")
    public ResponseEntity<?> getBooksPerAuthorView() {
        return ResponseEntity.ok(bookApplicationService.getBooksPerAuthorView());
    }
}
