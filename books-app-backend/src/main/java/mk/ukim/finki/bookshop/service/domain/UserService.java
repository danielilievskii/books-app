package mk.ukim.finki.bookshop.service.domain;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.bookshop.model.domain.User;
import mk.ukim.finki.bookshop.model.enumeration.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, String name, Role role);

    User login(String username, String password, HttpServletRequest request);

    void logout();

    User findByUsername(String username);

    User getAuthenticatedUser();
}
