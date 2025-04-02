package mk.ukim.finki.bookshop.dto;

public record UpdateAuthorDto(
        Long id,
        String name,
        String surname,
        Long country
) { }
