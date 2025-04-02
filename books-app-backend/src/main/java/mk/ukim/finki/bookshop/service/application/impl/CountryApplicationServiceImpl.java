package mk.ukim.finki.bookshop.service.application.impl;

import mk.ukim.finki.bookshop.dto.CreateCountryDto;
import mk.ukim.finki.bookshop.dto.DisplayCountryDto;
import mk.ukim.finki.bookshop.model.domain.Country;
import mk.ukim.finki.bookshop.service.application.CountryApplicationService;
import mk.ukim.finki.bookshop.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {
    private final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public List<DisplayCountryDto> findAll() {
        return DisplayCountryDto.from(countryService.findAll());
    }

    @Override
    public DisplayCountryDto findById(Long id) {
        return DisplayCountryDto.from(countryService.findById(id));
    }

    @Override
    public DisplayCountryDto add(CreateCountryDto createCountryDto) {
        Country country = createCountryDto.toEntity();
        return DisplayCountryDto.from(countryService.add(country));
    }

    @Override
    public DisplayCountryDto update(Long id, CreateCountryDto createCountryDto) {
        Country country = createCountryDto.toEntity();
        return DisplayCountryDto.from(countryService.update(id, country));
    }

    @Override
    public void deleteById(Long id) {
        countryService.deleteById(id);
    }
}
