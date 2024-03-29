package com.dwalldorf.trackmytime.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import com.dwalldorf.trackmytime.BaseTest;
import com.dwalldorf.trackmytime.exception.ResourceConflictException;
import com.dwalldorf.trackmytime.forms.user.RegisterForm;
import com.dwalldorf.trackmytime.model.HasUserId;
import com.dwalldorf.trackmytime.model.User;
import com.dwalldorf.trackmytime.repository.UserRepository;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceTest extends BaseTest {

    @Mock
    private SessionService mockSessionService;

    @Mock
    private PasswordEncoder mockPasswordEncoder;

    @Mock
    private UserRepository mockUserRepository;

    private UserService userService;

    @Override
    protected void setUp() {
        this.userService = new UserService(mockSessionService, mockPasswordEncoder, mockUserRepository);
    }

    @Test
    public void testRegister_EncodesPassword() {
        final String password = "password";
        RegisterForm registerForm = new RegisterForm()
                .setPassword(password);

        userService.register(registerForm);

        verify(mockPasswordEncoder).encode(eq(password));
    }

    @Test
    public void testRegister_SavesUser() {
        RegisterForm registerForm = new RegisterForm()
                .setUsername("username")
                .setEmail("mail@host.tld")
                .setPassword("password");

        userService.register(registerForm);

        verify(mockUserRepository).save(any(User.class));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsername_ThrowsUsernameNotFound() {
        final String username = "username";
        when(mockUserRepository.findByUsername(username)).thenReturn(null);

        userService.loadUserByUsername(username);
    }

    @Test
    public void testLoadUserByUsername() {
        final String username = "john";
        final String email = "john@host.tld";
        final String password = "ac7f27cb2be83415cad48f2257c00287c9d8e91fe4da9058a52f9319055dfeb3f6b403bf5c6e2a59";

        User mockDbUser = new User()
                .setUsername(username)
                .setEmail(email)
                .setPassword(password);
        when(mockUserRepository.findByUsername(eq(username))).thenReturn(mockDbUser);

        UserDetails userDetails = userService.loadUserByUsername(username);

        assertEquals(username, userDetails.getUsername());
        assertEquals(password, userDetails.getPassword());
        assertEquals(1, userDetails.getAuthorities().size());
    }

    @Test(expected = ResourceConflictException.class)
    public void testVerifyOwner_ThrowsResourceConflictException() {
        final String currentUserId = "123";
        final String objectUserId = "321";

        HasUserId mockObject = mock(HasUserId.class);
        when(mockObject.getUserId()).thenReturn(objectUserId);
        when(mockSessionService.getCurrentUserId()).thenReturn(currentUserId);

        userService.verifyOwner(mockObject);
    }

    @Test
    public void testVerifyOwner() {
        final String currentUserId = "123";
        final String objectUserId = currentUserId;

        HasUserId mockObject = mock(HasUserId.class);
        when(mockObject.getUserId()).thenReturn(objectUserId);
        when(mockSessionService.getCurrentUserId()).thenReturn(currentUserId);

        userService.verifyOwner(mockObject);
    }
}