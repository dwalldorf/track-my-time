package com.dwalldorf.trackmytime.service;

import com.dwalldorf.trackmytime.forms.user.RegisterForm;
import com.dwalldorf.trackmytime.model.User;
import com.dwalldorf.trackmytime.repository.UserRepository;
import javax.inject.Inject;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Inject
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User register(RegisterForm registerForm) {
        User user = new User()
                .setUsername(registerForm.getUsername())
                .setEmail(registerForm.getEmail())
                .setRegistration(new DateTime());

        user = userRepository.save(user);
        return user;
    }
}