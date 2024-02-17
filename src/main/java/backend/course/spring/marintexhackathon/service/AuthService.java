package backend.course.spring.marintexhackathon.service;

import backend.course.spring.marintexhackathon.dto.request.LoginRequest;
import backend.course.spring.marintexhackathon.dto.request.RefreshTokenRequest;
import backend.course.spring.marintexhackathon.dto.request.RegistrationRequest;
import backend.course.spring.marintexhackathon.dto.response.AuthenticationResponse;

public interface AuthService {

    String registration(RegistrationRequest registrationRequest);
    AuthenticationResponse login(LoginRequest loginRequest);
    AuthenticationResponse refreshToken(RefreshTokenRequest request);

}
