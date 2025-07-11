package mk.ukim.finki.bookshop.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.bookshop.dto.CreateAuthorDto;
import mk.ukim.finki.bookshop.service.application.AuthorApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/authors")
@Tag(name = "Authors", description = "Author management API for librarians")
public class AuthorController {

    private final AuthorApplicationService authorApplicationService;

    public AuthorController(AuthorApplicationService authorApplicationService) {
        this.authorApplicationService = authorApplicationService;
    }

    @GetMapping
    @Operation(summary = "List all authors")
    public ResponseEntity<?> listAllAuthors() {
        return ResponseEntity.status(HttpStatus.OK).body(authorApplicationService.findAll());
    }

    @GetMapping("/names")
    @Operation(summary = "List all author names")
    public ResponseEntity<?> listAllAuthorsNames() {
        return ResponseEntity.status(HttpStatus.OK).body(authorApplicationService.findAllAuthorsNames());
    }

    @GetMapping("/{id}")
    @Operation(summary = "List specific author")
    public ResponseEntity<?> listAuthor(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(authorApplicationService.findById(id));
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new author", description = "Adds an author with details provided in the request body")
    public ResponseEntity<?> addAuthor(@RequestBody CreateAuthorDto authorDto) {
        return ResponseEntity.ok(authorApplicationService.add(authorDto));
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Edit an author", description = "Edits an existing author's details")
    public ResponseEntity<?> editAuthor(@RequestBody CreateAuthorDto authorDto, @PathVariable Long id) {
        return ResponseEntity.ok(authorApplicationService.update(id, authorDto));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete an author", description = "Deletes an author by its ID")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        authorApplicationService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-country")
    public ResponseEntity<?> getAuthorsPerCountry() {
        return ResponseEntity.ok(authorApplicationService.getAuthorsPerCountry());
    }
}
