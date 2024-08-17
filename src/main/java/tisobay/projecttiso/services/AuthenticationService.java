package tisobay.projecttiso.services;

import tisobay.projecttiso.dto.request.AuthRequest;
import tisobay.projecttiso.dto.response.AuthResponse;
import tisobay.projecttiso.enums.Role;

public interface AuthenticationService {
    AuthResponse signIn(AuthRequest authRequest);
    AuthResponse signUp(AuthRequest authRequest);
    void checkAuth(Role role);
}
