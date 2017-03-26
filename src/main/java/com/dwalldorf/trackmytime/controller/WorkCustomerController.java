package com.dwalldorf.trackmytime.controller;

import static com.dwalldorf.trackmytime.controller.IndexController.URI_HOME;

import com.dwalldorf.trackmytime.model.Customer;
import com.dwalldorf.trackmytime.service.CustomerService;
import com.dwalldorf.trackmytime.service.UserService;
import com.dwalldorf.trackmytime.util.RouteUtil;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/work/customer")
public class WorkCustomerController {

    private final CustomerService customerService;

    private final UserService userService;

    @Inject
    public WorkCustomerController(CustomerService customerService, UserService userService) {
        this.customerService = customerService;
        this.userService = userService;
    }

    @GetMapping("/add")
    public String addPage(@ModelAttribute Customer customer) {
        return "work/customer/edit";
    }

    @PostMapping
    public String add(@ModelAttribute @Valid Customer customer) {
        customer.setUserId(userService.getCurrentUserId());
        customerService.save(customer);

        return RouteUtil.redirectString(URI_HOME);
    }
}