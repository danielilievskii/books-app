package mk.ukim.finki.bookshop.service.application;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.bookshop.dto.CreateUserDto;
import mk.ukim.finki.bookshop.dto.DisplayUserDto;
import mk.ukim.finki.bookshop.dto.LoginResponseDto;
import mk.ukim.finki.bookshop.dto.LoginUserDto;
import mk.ukim.finki.bookshop.model.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserApplicationService {

    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<LoginResponseDto> login(LoginUserDto loginUserDto, HttpServletRequest request);
    void logout();

    Optional<DisplayUserDto> findByUsername(String username);
    DisplayUserDto findAuthenticatedUser();
    List<User> findAll();
}
