package mk.ukim.finki.bookshop.service.application.impl;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.bookshop.dto.CreateUserDto;
import mk.ukim.finki.bookshop.dto.DisplayUserDto;
import mk.ukim.finki.bookshop.dto.LoginUserDto;
import mk.ukim.finki.bookshop.model.domain.User;
import mk.ukim.finki.bookshop.service.application.UserApplicationService;
import mk.ukim.finki.bookshop.service.domain.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;

    public UserApplicationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Optional<DisplayUserDto> register(CreateUserDto createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.repeatPassword(),
                createUserDto.name(),
                createUserDto.role()
        );
        return Optional.of(DisplayUserDto.from(user));
    }

    @Override
    public Optional<DisplayUserDto> login(LoginUserDto loginUserDto, HttpServletRequest request) {
        return Optional.of(DisplayUserDto.from(userService.login(
                loginUserDto.username(),
                loginUserDto.password(),
                request
        )));
    }

    @Override
    public void logout() {
        userService.logout();
    }

    @Override
    public Optional<DisplayUserDto> findByUsername(String username) {
        return Optional.of(DisplayUserDto.from(userService.findByUsername(username)));
    }

    @Override
    public List<User> fetchAll() {
        return userService.fetchAll();
    }

}
