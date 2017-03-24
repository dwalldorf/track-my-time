package com.dwalldorf.trackmytime.service;

import com.dwalldorf.trackmytime.exception.InvalidInputException;
import com.dwalldorf.trackmytime.forms.user.RegisterForm;
import com.dwalldorf.trackmytime.model.User;
import com.dwalldorf.trackmytime.repository.UserRepository;
import javax.inject.Inject;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final PasswordService passwordService;

    private final UserRepository userRepository;

    @Inject
    public UserService(PasswordService passwordService, UserRepository userRepository) {
        this.passwordService = passwordService;
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
            .setHashedPassword(passwordService.hash(registerForm.getPassword().toCharArray(), salt));
        //noinspection UnusedAssignment
        salt = null;

        return userRepository.save(user);
    }
}