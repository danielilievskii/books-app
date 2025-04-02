package mk.ukim.finki.bookshop.service.application;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.bookshop.dto.CreateUserDto;
import mk.ukim.finki.bookshop.dto.DisplayUserDto;
import mk.ukim.finki.bookshop.dto.LoginUserDto;

import java.util.Optional;

public interface UserApplicationService {

    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<DisplayUserDto> login(LoginUserDto loginUserDto, HttpServletRequest request);

    void logout();
    Optional<DisplayUserDto> findByUsername(String username);
}
