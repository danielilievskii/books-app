package mk.ukim.finki.bookshop.dto;

public record UpdateCountryDto(
        Long id,
        String name,
        String continent
) {}
