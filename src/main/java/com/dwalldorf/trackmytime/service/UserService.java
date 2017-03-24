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

        User user = userRepository.findByUsername(username);
        if (user != null) {
            throw new InvalidInputException("username or email already in use");
        }

        byte[] salt = passwordService.createSalt();
        user = new User()
                .setUsername(username)
                .setEmail(registerForm.getEmail())
                .setRegistration(new DateTime())
                .setSalt(salt)
                .setHashedPassword(passwordService.hash(registerForm.getPassword().toCharArray(), salt));
        //noinspection UnusedAssignment
        salt = null;

        return userRepository.save(user);
    }
}