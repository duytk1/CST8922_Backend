package com.algonquincollege.team7.service;

import com.algonquincollege.team7.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Service responsible for authenticating users by loading user details.
 *
 * Implements Spring Security's {@link UserDetailsService} to provide
 * user authentication functionality using email as the username.
 *
 * @see UserDetailsService
 * @see UserRepository
 */
@Service
public class AuthenticationService implements UserDetailsService {
    /**
     * Repository for accessing user data.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Loads user details by username (email) for authentication.
     *
     * @param username the email address identifying the user
     * @return UserDetails containing the user's authentication information
     * @throws UsernameNotFoundException if no user is found with the given email
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username);
    }
}
