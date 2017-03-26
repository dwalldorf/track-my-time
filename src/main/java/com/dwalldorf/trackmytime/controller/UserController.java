package com.dwalldorf.trackmytime.controller;

import com.dwalldorf.trackmytime.exception.InvalidFormInputException;
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
        } catch (InvalidFormInputException e) {
            ObjectError objectError = e.toFormError(bindingResult.getObjectName());
            bindingResult.addError(objectError);

            return registerPage(registerForm);
        }

        return "redirect:/login";
    }
}