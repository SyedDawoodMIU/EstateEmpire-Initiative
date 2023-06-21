package property.application.service;


import property.application.dto.request.LoginRequest;
import property.application.dto.request.RefreshTokenRequest;
import property.application.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);

    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
