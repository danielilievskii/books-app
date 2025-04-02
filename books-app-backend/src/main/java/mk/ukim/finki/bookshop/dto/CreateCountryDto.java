package mk.ukim.finki.bookshop.dto;

import mk.ukim.finki.bookshop.model.domain.Country;

public record CreateCountryDto(
        String name,
        String continent
) {
    public Country toEntity() {
        return new Country(
                this.name,
                this.continent
        );
    }
}
