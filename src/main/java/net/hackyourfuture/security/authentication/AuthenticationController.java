package net.hackyourfuture.security.authentication;

import lombok.AllArgsConstructor;
import net.hackyourfuture.security.authentication.dto.LoginRequest;
import net.hackyourfuture.security.authentication.dto.LoginResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        return authenticationService.login(request);
    }

    @PostMapping("/logout")
    public void logout() {
        authenticationService.logout();
    }
}
