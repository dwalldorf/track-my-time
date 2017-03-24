package com.dwalldorf.trackmytime.controller;

import com.dwalldorf.trackmytime.exception.InvalidInputException;
import com.dwalldorf.trackmytime.forms.user.LoginForm;
import com.dwalldorf.trackmytime.forms.user.RegisterForm;
import com.dwalldorf.trackmytime.service.UserService;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(@ModelAttribute LoginForm loginForm) {
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute @Valid LoginForm loginForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return loginPage(loginForm);
        }
        return "redirect:/";
    }

    @GetMapping("/register")
    public String registerPage(@ModelAttribute RegisterForm registerForm) {
        return "user/register";
    }

    @PostMapping("/register")
    public String register(@Valid RegisterForm registerForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return registerPage(registerForm);
        }

        try {
            userService.register(registerForm);
        } catch (InvalidInputException e) {
            ObjectError fieldError = new ObjectError("registerForm", e.getMessage());
            bindingResult.addError(fieldError);
            return registerPage(registerForm);
        }

        return "redirect:/login";
    }
}