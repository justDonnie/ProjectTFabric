package tisobay.projecttiso.services.impl;

import jakarta.annotation.PostConstruct;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tisobay.projecttiso.dto.request.AuthRequest;
import tisobay.projecttiso.dto.response.AuthResponse;
import tisobay.projecttiso.entities.User;
import tisobay.projecttiso.enums.Role;
import tisobay.projecttiso.exceptions.AccessDeniedException;
import tisobay.projecttiso.exceptions.AlreadyExistException;
import tisobay.projecttiso.exceptions.BadCredentialException;
import tisobay.projecttiso.exceptions.NotFoundException;
import tisobay.projecttiso.repositories.UserRepository;
import tisobay.projecttiso.security.jwtConfig.JwtService;
import tisobay.projecttiso.services.AuthenticationService;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse signIn(AuthRequest authRequest) {
        log.info("Check User method is started...");
        log.info("Received email in sign in method: "+authRequest.getEmail());
        checkUser(authRequest.getEmail());
        log.info("Sign In method is started...");
        if (authRequest.getEmail() == null || authRequest.getEmail().isBlank()) {
            log.error("Email is null or blanked !!!");
            throw new BadCredentialException("Email is null or blank!!!");
        }
        User user = userRepository.getUserByEmail(authRequest.getEmail()).orElseThrow(
                () -> new NotFoundException("There is no any user's with this email!!!"));
        log.info("User's email: " + user.getEmail());

        log.info("Checking password...");
        if (!passwordEncoder.matches(authRequest.getPassword(),user.getPassword())) {
            log.info("Wrong password !!!");
            throw new BadCredentialException("Wrong password!!!");
        }
        log.info("Token for sign in method is generating...");
        String token = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(token)
                .email(user.getEmail())
                .build();
    }


    @Transactional
    @Override
    public AuthResponse signUp(AuthRequest authRequest) {
        log.info("Getting user's email...");
        if (userRepository.existsByEmail(authRequest.getEmail())) {
            throw new AlreadyExistException(
                    String.format("Already exists user with email: %s ", authRequest.getEmail()));
        }
        log.info("User is authorizing...");
        User user = new User();
        user.setEmail(authRequest.getEmail());
        user.setPassword(passwordEncoder.encode(authRequest.getPassword()));
        user.setRole(Role.USER);
        log.info("User authorized...");
        userRepository.save(user);
        log.info("Token for sign up method is generating...");
        String token = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(token)
                .email(user.getEmail())
                .build();
    }

    @Override
    public void checkAuth(Role role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("Требуется аутентификация для выполнения этого действия.");
        }
        String email = authentication.getName();
        User user = userRepository.getUserByEmail(email).orElseThrow(() -> {
            log.error("Пользователь с email не найден: " + email);
            return new NotFoundException("Пользователь не найден.");
        });
        if (!user.getRole().equals(Role.ADMIN)) {
            throw new AccessDeniedException("Только администратор может выполнять это действие.");
        }
    }

    public void checkUser(String email) {
        Optional<User> user = userRepository.getUserByEmail(email);
        if (user.isPresent()) {
            System.out.println("User found: " + user.get().getEmail());
        } else {
            System.out.println("User not found");
        }
    }

    @PostConstruct
    public void initSaveAdmin() {
        log.info("Admin is creating...");
        User user = User.builder()
                .email("admin@gmail.com")
                .password(passwordEncoder.encode("admin123"))
                .role(Role.ADMIN)
                .build();
        log.info("Admin is created !!!");
        if (!userRepository.existsByEmail(user.getEmail())) {
            userRepository.save(user);
            log.info("Admin successfully created and saved");
        }
    }
}
