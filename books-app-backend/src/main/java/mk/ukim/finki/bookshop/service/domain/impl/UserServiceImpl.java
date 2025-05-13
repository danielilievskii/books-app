package mk.ukim.finki.bookshop.service.domain.impl;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.bookshop.model.domain.User;
import mk.ukim.finki.bookshop.model.enumeration.Role;
import mk.ukim.finki.bookshop.model.exception.AuthenticationException;
import mk.ukim.finki.bookshop.model.exception.InvalidArgumentsException;
import mk.ukim.finki.bookshop.model.exception.PasswordsDoNotMatchException;
import mk.ukim.finki.bookshop.model.exception.UsernameAlreadyExistsException;
import mk.ukim.finki.bookshop.repository.UserRepository;
import mk.ukim.finki.bookshop.service.domain.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, @Lazy AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                username));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                username));
    }

    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken)  {
            throw new AuthenticationException();
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public List<User> fetchAll() {
        return userRepository.fetchAll();
    }

    @Override
    public User register(
            String username,
            String password,
            String repeatPassword,
            String name,
            Role userRole
    ) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidArgumentsException();

        if (!password.equals(repeatPassword)) throw new PasswordsDoNotMatchException();

        if (userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);

        User user = new User(username, passwordEncoder.encode(password), name, userRole);
        return userRepository.save(user);
    }

    @Override
    public User login(String username, String password, HttpServletRequest request) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentsException();
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        User user = findByUsername(username);
        setAuthentication(user, request);

        return user;
    }

    @Override
    public void logout() {
        if(SecurityContextHolder.getContext().getAuthentication() != null) {
            SecurityContextHolder.clearContext();
        } else {
            throw new AuthenticationException();
        }
    }

    public void setAuthentication(UserDetails userDetails, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}