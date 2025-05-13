package mk.ukim.finki.bookshop.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.bookshop.dto.DisplayUserDto;
import mk.ukim.finki.bookshop.dto.LoginResponseDto;
import mk.ukim.finki.bookshop.service.application.UserApplicationService;
import mk.ukim.finki.bookshop.dto.CreateUserDto;
import mk.ukim.finki.bookshop.dto.LoginUserDto;
import mk.ukim.finki.bookshop.model.exception.InvalidArgumentsException;
import mk.ukim.finki.bookshop.model.exception.PasswordsDoNotMatchException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "User API", description = "Endpoints for user authentication and registration") // Swagger tag
public class UserController {

    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @Operation(summary = "Register a new user", description = "Creates a new user account")
    @PostMapping("/signup")
    public ResponseEntity<DisplayUserDto> register(@RequestBody CreateUserDto createUserDto) {
        try {
            return userApplicationService.register(createUserDto)
                                         .map(ResponseEntity::ok)
                                         .orElse(ResponseEntity.notFound().build());
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "User login", description = "Authenticates a user and starts a session")
    @PostMapping("/signin")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginUserDto loginUserDto, HttpServletRequest request) {
        return ResponseEntity.ok(userApplicationService.login(loginUserDto, request).get());
    }

    @Operation(summary = "User logout", description = "Ends the user's session")
    @GetMapping("/signout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        userApplicationService.logout();
        return ResponseEntity.ok().build();

    }

    @Operation(summary = "Returns current authenticated user")
    @GetMapping("/me")
    public ResponseEntity<DisplayUserDto> fetchAuthenticatedUser() {
        return ResponseEntity.ok(userApplicationService.findAuthenticatedUser());
    }

    @Operation(summary = "Fetches all users")
    @GetMapping("/users")
    public ResponseEntity<?> findAll(HttpServletRequest request) {
        return ResponseEntity.ok(userApplicationService.findAll());

    }
}