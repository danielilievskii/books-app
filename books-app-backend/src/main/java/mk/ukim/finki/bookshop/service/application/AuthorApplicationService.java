package mk.ukim.finki.bookshop.service.application;

import mk.ukim.finki.bookshop.dto.CreateAuthorDto;
import mk.ukim.finki.bookshop.dto.CreateCountryDto;
import mk.ukim.finki.bookshop.dto.DisplayAuthorDto;
import mk.ukim.finki.bookshop.dto.DisplayCountryDto;
import mk.ukim.finki.bookshop.model.projections.AuthorProjection;
import mk.ukim.finki.bookshop.model.views.AuthorsPerCountryView;

import java.util.List;

public interface AuthorApplicationService {

    List<DisplayAuthorDto> findAll();
    List<AuthorProjection> findAllAuthorsNames();
    DisplayAuthorDto findById(Long id);

    DisplayAuthorDto add(CreateAuthorDto createAuthorDto);
    DisplayAuthorDto update(Long id, CreateAuthorDto createAuthorDto);
    void deleteById(Long id);

    List<AuthorsPerCountryView> getAuthorsPerCountry();
}
