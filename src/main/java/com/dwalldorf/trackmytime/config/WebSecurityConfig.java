package com.dwalldorf.trackmytime.config;

import javax.inject.Inject;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_URL = "/login";
    private static final String[] PUBLIC_ROUTES = {
            LOGIN_URL,
            "/register",

            "/css/**",
            "/vendor/**"
    };

    private final DaoAuthenticationProvider authenticationProvider;

    @Inject
    public WebSecurityConfig(DaoAuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    /**
     * Configure access to parts of the application
     *
     * @param http HttpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(PUBLIC_ROUTES).permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin().loginPage(LOGIN_URL)
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl(LOGIN_URL)
            .permitAll();
    }

    /**
     * Use {@link AuthProviderConfig#authenticationProvider()} <code>AuthenticationProvider</code>.
     *
     * @param auth AuthenticationManagerBuilder
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }
}