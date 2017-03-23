package com.dwalldorf.trackmytime.service;

import com.dwalldorf.trackmytime.repository.UserRepository;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Inject
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}