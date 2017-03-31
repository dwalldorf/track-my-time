package com.dwalldorf.trackmytime.controller;

import com.dwalldorf.trackmytime.exception.InvalidFormInputException;
import com.dwalldorf.trackmytime.forms.user.RegisterForm;
import com.dwalldorf.trackmytime.model.Customer;
import com.dwalldorf.trackmytime.model.User;
import com.dwalldorf.trackmytime.service.CustomerService;
import com.dwalldorf.trackmytime.service.UserService;
import com.dwalldorf.trackmytime.util.RouteUtil;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    // TODO: make constants for view paths

    private final UserService userService;

    private final CustomerService customerService;

    @Inject
    public UserController(UserService userService, CustomerService customerService) {
        this.userService = userService;
        this.customerService = customerService;
    }

    @ModelAttribute("allCustomers")
    public List<Customer> allCustomers() {
        return customerService.findAllByUser(userService.getCurrentUserId());
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

        // TODO: use RouteUtil
        return "redirect:/login";
    }

    @GetMapping("/user/edit")
    public ModelAndView editPage() {
        ModelAndView mav = new ModelAndView("user/edit");

        User user = userService.getCurrentUser();
        mav.addObject("user", user);

        return mav;
    }

    @PostMapping("/user/edit")
    public String update() {
        return RouteUtil.redirectString("/user/edit");
    }
}