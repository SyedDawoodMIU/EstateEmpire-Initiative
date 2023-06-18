package property.application.service;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import property.application.model.request.LoginRequest;
import property.application.model.request.RefreshTokenRequest;
import property.application.model.response.LoginResponse;

import java.io.IOException;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);

    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
