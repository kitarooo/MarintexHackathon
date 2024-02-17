package backend.course.spring.marintexhackathon.service.impl;

import backend.course.spring.marintexhackathon.dto.request.LoginRequest;
import backend.course.spring.marintexhackathon.dto.request.RefreshTokenRequest;
import backend.course.spring.marintexhackathon.dto.request.RegistrationRequest;
import backend.course.spring.marintexhackathon.dto.response.AuthenticationResponse;
import backend.course.spring.marintexhackathon.entity.User;
import backend.course.spring.marintexhackathon.enums.Role;
import backend.course.spring.marintexhackathon.exception.BaseException;
import backend.course.spring.marintexhackathon.exception.NotFoundException;
import backend.course.spring.marintexhackathon.exception.UserAlreadyExistException;
import backend.course.spring.marintexhackathon.repository.UserRepository;
import backend.course.spring.marintexhackathon.security.jwt.JwtService;
import backend.course.spring.marintexhackathon.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public String registration(RegistrationRequest registrationRequest) {
        if (userRepository.findByEmail(registrationRequest.getEmail()).isPresent()) {
            throw new UserAlreadyExistException("Пользователь с email: " + registrationRequest.getEmail() + " уже существует!");
        }

        if (userRepository.findByUsername(registrationRequest.getUsername()).isPresent()) {
            throw new UserAlreadyExistException("Пользователь с username: " + registrationRequest.getUsername() + " уже существует!");
        }

        User user = User.builder()
                .username(registrationRequest.getUsername())
                .email(registrationRequest.getEmail())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .role(Role.ROLE_USER)
                .build();
        userRepository.save(user);

        return "Регистрация прошла успешно!";
    }

    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new NotFoundException("Пользователь не найден!"));

        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            var jwtToken = jwtService.generateToken(user);
            var refreshToken = jwtService.generateRefreshToken(user);
            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .refreshToken(refreshToken)
                    .build();
        } else {
            throw new BaseException("Имя пользователя или пароль введены неправильно!");
        }
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshTokenRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new NotFoundException("Такой пользователь не сущуествует!"));

        if (jwtService.isTokenValid(request.getToken(), user)) {
            return AuthenticationResponse.builder()
                    .refreshToken(jwtService.generateRefreshToken(user))
                    .accessToken(jwtService.generateToken(user))
                    .build();
        } else {
            throw new BaseException("Время токена истекло!");
        }
    }
}
