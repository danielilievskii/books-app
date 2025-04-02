package mk.ukim.finki.bookshop.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mk.ukim.finki.bookshop.dto.CreateBookDto;
import mk.ukim.finki.bookshop.dto.CreateCountryDto;
import mk.ukim.finki.bookshop.exception.AuthorNotFoundException;
import mk.ukim.finki.bookshop.exception.BookNotFoundException;
import mk.ukim.finki.bookshop.exception.BookOutOfStockException;
import mk.ukim.finki.bookshop.exception.CountryNotFoundException;
import mk.ukim.finki.bookshop.service.application.BookApplicationService;
import mk.ukim.finki.bookshop.service.application.CountryApplicationService;
import mk.ukim.finki.bookshop.service.domain.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/countries")
@Tag(name = "Countries", description = "Country management API for librarians")
public class CountryController {

    private final CountryApplicationService countryApplicationService;

    public CountryController(CountryApplicationService countryApplicationService) {
        this.countryApplicationService = countryApplicationService;
    }

    @GetMapping
    @Operation(summary = "List all countries")
    public ResponseEntity<?> listAllCountries() {
        return ResponseEntity.status(HttpStatus.OK).body(countryApplicationService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "List specific country")
    public ResponseEntity<?> listCountry(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(countryApplicationService.findById(id));
    }

    @PostMapping("/add")
    @Operation(summary = "Add a new country", description = "Adds a country with details provided in the request body")
    public ResponseEntity<?> addCountry(@RequestBody CreateCountryDto countryDto) {
        return ResponseEntity.ok(countryApplicationService.add(countryDto));
    }

    @PutMapping("/edit/{id}")
    @Operation(summary = "Edit a country", description = "Edits an existing country's details")
    public ResponseEntity<?> editCountry(@RequestBody CreateCountryDto countryDto, @PathVariable Long id) {
        return ResponseEntity.ok(countryApplicationService.update(id, countryDto));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a country", description = "Deletes a country by its ID")
    public ResponseEntity<?> deleteCountry(@PathVariable Long id) {
        countryApplicationService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
