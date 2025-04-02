package mk.ukim.finki.bookshop.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.java.Log;
import mk.ukim.finki.bookshop.dto.DisplayUserDto;
import mk.ukim.finki.bookshop.service.application.UserApplicationService;
import mk.ukim.finki.bookshop.dto.CreateUserDto;
import mk.ukim.finki.bookshop.dto.DisplayUserDto;
import mk.ukim.finki.bookshop.dto.LoginUserDto;
import mk.ukim.finki.bookshop.exception.InvalidArgumentsException;
import mk.ukim.finki.bookshop.exception.InvalidUserCredentialsException;
import mk.ukim.finki.bookshop.exception.PasswordsDoNotMatchException;
import mk.ukim.finki.bookshop.service.application.UserApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
//    @ApiResponses(
//            value = {@ApiResponse(
//                    responseCode = "200",
//                    description = "User registered successfully"
//            ), @ApiResponse(
//                    responseCode = "400", description = "Invalid input or passwords do not match"
//            )}
//    )
    @PostMapping("/register")
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
//    @ApiResponses(
//            value = {@ApiResponse(
//                    responseCode = "200",
//                    description = "User authenticated successfully"
//            ), @ApiResponse(responseCode = "404", description = "Invalid username or password")}
//    )
    @PostMapping("/login")
    public ResponseEntity<DisplayUserDto> login(@RequestBody LoginUserDto loginUserDto, HttpServletRequest request) {
        return ResponseEntity.ok(userApplicationService.login(loginUserDto, request).get());
    }

    @Operation(summary = "User logout", description = "Ends the user's session")
//    @ApiResponse(responseCode = "200", description = "User logged out successfully")
    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        userApplicationService.logout();
        return ResponseEntity.ok().build();

    }
}