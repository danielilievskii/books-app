package mk.ukim.finki.bookshop.dto;

import mk.ukim.finki.bookshop.model.domain.User;
import mk.ukim.finki.bookshop.model.enumeration.Role;

public record CreateUserDto(
        String username,
        String password,
        String repeatPassword,
        String name,
        String surname,
        Role role
) {

    public User toUser() {
        return new User(username, password, name, role);
    }
}
