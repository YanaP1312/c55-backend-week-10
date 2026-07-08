package net.hackyourfuture.security.user;

import lombok.AllArgsConstructor;
import net.hackyourfuture.security.user.dto.UserRequest;
import net.hackyourfuture.security.user.dto.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse register(UserRequest request) {
        String encoderPassword = passwordEncoder.encode(request.password());
        User newUser = new User(UUID.randomUUID().toString(), request.username(), encoderPassword);

        userRepository.createUser(newUser);

        return new UserResponse(newUser.getId(), newUser.getUsername());
    }

    @PreAuthorize("#username == authentication.name")
    public UserResponse getProfile(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return new UserResponse(user.getId(), user.getUsername());
    }
}
