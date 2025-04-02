package mk.ukim.finki.bookshop.service.application;

import mk.ukim.finki.bookshop.dto.CreateCountryDto;
import mk.ukim.finki.bookshop.dto.DisplayCountryDto;

import java.util.List;

public interface CountryApplicationService {

    List<DisplayCountryDto> findAll();
    DisplayCountryDto findById(Long id);

    DisplayCountryDto add(CreateCountryDto country);
    DisplayCountryDto update(Long id, CreateCountryDto country);
    void deleteById(Long id);
}
