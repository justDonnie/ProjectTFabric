package tisobay.projecttiso.api;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tisobay.projecttiso.dto.request.AuthRequest;
import tisobay.projecttiso.dto.response.AuthResponse;
import tisobay.projecttiso.services.AuthenticationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Auth API")
@Slf4j
public class AuthenticationApi {
    private final AuthenticationService authenticationService;

    @PostMapping("/signIn")
    public AuthResponse signIn(@RequestBody AuthRequest request){
        log.info("Received raw email for sign in method : "+request.getEmail());
        return authenticationService.signIn(request);
    }

    @PostMapping("/signUp")
    public AuthResponse signUp(@RequestBody AuthRequest authRequest) {
        log.info("Received raw email for sign up method : "+authRequest.getEmail());
        return authenticationService.signUp(authRequest);
    }
}
