package mk.ukim.finki.bookshop.dto;

import mk.ukim.finki.bookshop.model.enumeration.Category;

public record UpdateBookDto(
        Long id,
        String name,
        Category category,
        Long author,
        Integer availableCopies

) {}

