package com.algonquincollege.team7.service;

import com.algonquincollege.team7.dto.SignupRequest;
import com.algonquincollege.team7.infra.exception.ApiException;
import com.algonquincollege.team7.model.User;
import com.algonquincollege.team7.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class handling user-related business logic.
 *
 * Provides functionality for user registration and account management,
 * including password encryption and duplicate user validation.
 *
 * @see User
 * @see UserRepository
 * @see SignupRequest
 */
@Service
public class UserService {

    /**
     * Password encoder for securely hashing user passwords.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Repository for user data access and persistence.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Constructs a new UserService with the required password encoder.
     *
     * @param passwordEncoder the password encoder to use for securing passwords
     */
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user in the system.
     *
     * @param data the user registration data
     * @throws ApiException with CONFLICT status (409) if email is already registered
     */
    public void signupUser(SignupRequest data) {
        var user = new User(data);

        var duplicatedUser = userRepository.existsByEmail(data.email());
        if (duplicatedUser) {
            throw new ApiException(HttpStatus.CONFLICT, "User already registered");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
