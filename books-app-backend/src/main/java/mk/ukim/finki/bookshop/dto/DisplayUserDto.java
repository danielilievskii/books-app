package mk.ukim.finki.bookshop.dto;

import mk.ukim.finki.bookshop.model.domain.User;
import mk.ukim.finki.bookshop.model.enumeration.Role;

public record DisplayUserDto(Long id, String username, String name, Role role) {

    public static DisplayUserDto from(User user) {
        return new DisplayUserDto(
                user.getId(),
                user.getUsername(),
                user.getName(),
                user.getRole()
        );
    }
}
