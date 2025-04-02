package mk.ukim.finki.bookshop.dto;

import mk.ukim.finki.bookshop.model.domain.Country;

import java.util.List;

public record DisplayCountryDto(
        Long id,
        String name,
        String continent
) {
    public static DisplayCountryDto from(Country country) {
        if(country != null) {
            return new DisplayCountryDto(
                    country.getId(),
                    country.getName(),
                    country.getContinent()
            );
        } else return null;
    }

    public static List<DisplayCountryDto> from (List<Country> countries) {
        return countries.stream().map(DisplayCountryDto::from).toList();
    }
}
