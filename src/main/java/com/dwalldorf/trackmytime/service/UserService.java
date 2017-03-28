package com.dwalldorf.trackmytime.service;

import com.dwalldorf.trackmytime.config.SecurityUser;
import com.dwalldorf.trackmytime.exception.InvalidFormInputException;
import com.dwalldorf.trackmytime.exception.ResourceConflictException;
import com.dwalldorf.trackmytime.forms.user.RegisterForm;
import com.dwalldorf.trackmytime.model.HasUserId;
import com.dwalldorf.trackmytime.model.User;
import com.dwalldorf.trackmytime.repository.UserRepository;
import java.util.Collections;
import javax.inject.Inject;
import org.joda.time.DateTime;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Inject
    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    /**
     * Creates a user. Will encode the password and set registration date.
     *
     * @param registerForm user data
     * @return persisted user
     * @throws InvalidFormInputException in case of validation error
     */
    @Transactional
    public User register(RegisterForm registerForm) throws InvalidFormInputException {
        final String username = registerForm.getUsername();
        final String email = registerForm.getEmail();

        if (userRepository.findByUsername(username) != null) {
            throw new InvalidFormInputException("username", "username already in use", username);
        }
        if (userRepository.findByEmail(email) != null) {
            throw new InvalidFormInputException("email", "email already in use", email);
        }

        User user = new User()
                .setUsername(username)
                .setEmail(email)
                .setRegistration(new DateTime())
                .setPassword(passwordEncoder.encode(registerForm.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found: " + username);
        }

        return new SecurityUser(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("USER")),
                user.getId()
        );
    }

    public String getCurrentUserId() {
        return getCurrentUser().getId();
    }

    /**
     * Verifies the object to modify belongs to the current user.
     *
     * @param objectToModify object to perform check on
     * @throws ResourceConflictException in case the object belongs to another user
     */
    public void verifyOwner(HasUserId objectToModify) throws ResourceConflictException {
        if (objectToModify.getUserId() == null) {
            return;
        }
        if (!objectToModify.getUserId().equals(getCurrentUserId())) {
            String message = String.format(
                    "User %s tried to modify %s of type %s but belongs to %s",
                    getCurrentUser(),
                    objectToModify.getId(),
                    objectToModify.getObjectType(),
                    objectToModify.getUserId()
            );
            throw new ResourceConflictException(message);
        }
    }

    private SecurityUser getCurrentUser() {
        return (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}