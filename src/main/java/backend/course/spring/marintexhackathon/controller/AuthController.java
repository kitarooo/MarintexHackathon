package backend.course.spring.marintexhackathon.controller;

import backend.course.spring.marintexhackathon.dto.request.LoginRequest;
import backend.course.spring.marintexhackathon.dto.request.RefreshTokenRequest;
import backend.course.spring.marintexhackathon.dto.request.RegistrationRequest;
import backend.course.spring.marintexhackathon.dto.response.AuthenticationResponse;
import backend.course.spring.marintexhackathon.service.impl.AuthServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthServiceImpl authService;

    @PostMapping("/registration")
    @Operation(summary = "User registration/регистрация", description = "Регистрация, по умолчанию если пользователь еще не подтвердил через почту - у него будет статус INACTIVE! Если такой пользователь уже существует сервер выдает 403 и Exception: UserAlreadyException.")
    public String registration(@RequestBody RegistrationRequest request) {
        return authService.registration(request);
    }

    @PostMapping("/login")
    @Operation(summary = "Авторизация", description = "Выдает JWT  и Refresh token после авторизации")
    public AuthenticationResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/refreshToken")
    @Operation(summary = "Refresh token", description = "Обновляет JWT токен если выданный JWT уже истек, действует 6 часов")
    public AuthenticationResponse refreshToken(@RequestBody RefreshTokenRequest request) {
        return authService.refreshToken(request);
    }
}
