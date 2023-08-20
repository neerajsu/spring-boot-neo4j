package dev.farhan.springbootneo4j.controllers;

import dev.farhan.springbootneo4j.models.User;
import dev.farhan.springbootneo4j.objects.UserDTO;
import dev.farhan.springbootneo4j.requests.CreateUserRequest;
import dev.farhan.springbootneo4j.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public String loggedInUser(Principal principal) {
        return principal.getName();
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> signUp(@RequestBody CreateUserRequest userRequest) {
        User user = userService.createUser(userRequest);

        UserDTO userDTO = UserDTO.builder()
                .name(user.getName())
                .username(user.getUsername())
                .roles(user.getRoles())
                .build();

        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }
}
