package com.dwalldorf.trackmytime.service;

import com.dwalldorf.trackmytime.exception.InvalidInputException;
import com.dwalldorf.trackmytime.forms.user.RegisterForm;
import com.dwalldorf.trackmytime.model.User;
import com.dwalldorf.trackmytime.repository.UserRepository;
import java.util.Collections;
import javax.inject.Inject;
import org.joda.time.DateTime;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    private final PasswordService passwordService;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Inject
    public UserService(PasswordService passwordService, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordService = passwordService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Transactional
    public User register(RegisterForm registerForm) throws InvalidInputException {
        final String username = registerForm.getUsername();
        final String email = registerForm.getEmail();

        if (userRepository.findByUsername(username) != null) {
            throw new InvalidInputException("username", "username already in use", username);
        }
        if (userRepository.findByEmail(email) != null) {
            throw new InvalidInputException("email", "email already in use", email);
        }

        User user = new User();
        byte[] salt = passwordService.createSalt();
        user.setUsername(username)
            .setEmail(email)
            .setRegistration(new DateTime())
            .setSalt(salt)
            .setHashedPassword(passwordService.hash(registerForm.getPassword().toCharArray(), salt))
            .setPassword(passwordEncoder.encode(registerForm.getPassword()));
        //noinspection UnusedAssignment
        salt = null;

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found: " + username);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("USER"))
        );
    }
}