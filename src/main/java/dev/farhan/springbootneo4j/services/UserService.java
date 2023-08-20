package dev.farhan.springbootneo4j.services;

import dev.farhan.springbootneo4j.models.User;
import dev.farhan.springbootneo4j.repositories.UserRepository;
import dev.farhan.springbootneo4j.requests.CreateUserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(CreateUserRequest createUserRequest) {

        User user = new User();
        user.setName(createUserRequest.getName());
        user.setRoles(createUserRequest.getRoles());
        user.setUsername(createUserRequest.getUsername());
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));

        userRepository.save(user);

        return user;
    }

}