package mk.ukim.finki.bookshop.dto;

import mk.ukim.finki.bookshop.model.domain.Author;
import mk.ukim.finki.bookshop.model.domain.Country;

public record CreateAuthorDto(
        String name,
        String surname,
        Long country
) {
    public Author toEntity(Country country) {
        return new Author(
                this.name,
                this.surname,
                country
        );
    }
}
