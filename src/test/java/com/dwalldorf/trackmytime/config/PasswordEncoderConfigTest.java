package com.dwalldorf.trackmytime.config;

import static org.junit.Assert.assertTrue;

import com.dwalldorf.trackmytime.BaseTest;
import org.junit.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

public class PasswordEncoderConfigTest extends BaseTest {

    private PasswordEncoderConfig passwordEncoderConfig;

    @Override
    protected void setUp() {
        this.passwordEncoderConfig = new PasswordEncoderConfig();
    }

    @Test
    public void testPasswordEncoder() {
        PasswordEncoder passwordEncoder = passwordEncoderConfig.passwordEncoder();
        assertTrue(passwordEncoder instanceof Pbkdf2PasswordEncoder);
    }
}