package mk.ukim.finki.bookshop.service.domain.impl;

import mk.ukim.finki.bookshop.model.exception.CountryNotFoundException;
import mk.ukim.finki.bookshop.model.domain.Author;
import mk.ukim.finki.bookshop.model.domain.Country;
import mk.ukim.finki.bookshop.repository.AuthorRepository;
import mk.ukim.finki.bookshop.repository.CountryRepository;
import mk.ukim.finki.bookshop.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final AuthorRepository authorRepository;

    public CountryServiceImpl(CountryRepository countryRepository, AuthorRepository authorRepository) {
        this.countryRepository = countryRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country findById(Long id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));
    }

    @Override
    public Country add(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Country update(Long id, Country country) {
        Country countryTarget = findById(id);

        countryTarget.setName(country.getName());
        countryTarget.setContinent(country.getContinent());

        return countryRepository.save(countryTarget);
    }

    @Override
    public void deleteById(Long id) {
        Country countryTarget = findById(id);

        for (Author author : countryTarget.getAuthorList()) {
            author.setCountry(null);
            authorRepository.save(author);
        }
        countryRepository.delete(countryTarget);
    }
}
