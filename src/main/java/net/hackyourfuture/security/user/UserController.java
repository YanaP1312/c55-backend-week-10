package net.hackyourfuture.security.user;

import lombok.AllArgsConstructor;
import net.hackyourfuture.security.user.dto.UserRequest;
import net.hackyourfuture.security.user.dto.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@RequestBody UserRequest request) {
        return userService.register(request);
    }



    @GetMapping("/profile")
    public UserResponse profile(Authentication authentication) {

        return userService.getProfile(authentication.getName());
    }
}
