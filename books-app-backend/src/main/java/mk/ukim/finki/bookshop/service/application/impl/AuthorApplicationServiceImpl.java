package mk.ukim.finki.bookshop.service.application.impl;

import mk.ukim.finki.bookshop.dto.CreateAuthorDto;
import mk.ukim.finki.bookshop.dto.DisplayAuthorDto;
import mk.ukim.finki.bookshop.model.domain.Author;
import mk.ukim.finki.bookshop.model.domain.Country;
import mk.ukim.finki.bookshop.model.projections.AuthorProjection;
import mk.ukim.finki.bookshop.model.views.AuthorsPerCountryView;
import mk.ukim.finki.bookshop.service.application.AuthorApplicationService;
import mk.ukim.finki.bookshop.service.domain.AuthorService;
import mk.ukim.finki.bookshop.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorApplicationServiceImpl implements AuthorApplicationService {

    private final AuthorService authorService;
    private final CountryService countryService;

    public AuthorApplicationServiceImpl(AuthorService authorService, CountryService countryService) {
        this.authorService = authorService;
        this.countryService = countryService;
    }

    @Override
    public List<DisplayAuthorDto> findAll() {
        return DisplayAuthorDto.from(authorService.findAll());
    }

    @Override
    public List<AuthorProjection> findAllAuthorsNames() {
        return authorService.findAllAuthorsNames();
    }

    @Override
    public DisplayAuthorDto findById(Long id) {
        return DisplayAuthorDto.from(authorService.findById(id));
    }

    @Override
    public DisplayAuthorDto add(CreateAuthorDto createAuthorDto) {
        Country country = countryService.findById(createAuthorDto.country());
        Author author = createAuthorDto.toEntity(country);

        return DisplayAuthorDto.from(authorService.add(author));
    }

    @Override
    public DisplayAuthorDto update(Long id, CreateAuthorDto createAuthorDto) {
        Country country = countryService.findById(createAuthorDto.country());
        Author author = createAuthorDto.toEntity(country);

        return DisplayAuthorDto.from(authorService.update(id, author));
    }

    @Override
    public void deleteById(Long id) {
        authorService.deleteById(id);
    }

    @Override
    public List<AuthorsPerCountryView> getAuthorsPerCountry() {
        return authorService.getAuthorsPerCountry();
    }
}
