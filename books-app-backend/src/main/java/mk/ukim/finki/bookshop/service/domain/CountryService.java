package mk.ukim.finki.bookshop.service.domain;

import mk.ukim.finki.bookshop.model.domain.Country;

import java.util.List;

public interface CountryService {

    List<Country> findAll();
    Country findById(Long id);

    Country add(Country country);
    Country update(Long id, Country country);
    void deleteById(Long id);
}
