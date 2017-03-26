package com.dwalldorf.trackmytime.controller;

import static com.dwalldorf.trackmytime.controller.IndexController.URI_HOME;

import com.dwalldorf.trackmytime.model.Customer;
import com.dwalldorf.trackmytime.service.CustomerService;
import com.dwalldorf.trackmytime.service.UserService;
import com.dwalldorf.trackmytime.util.RouteUtil;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    @ModelAttribute("allCustomers")
    public List<Customer> allCustomers() {
        return customerService.findAllByUser(userService.getCurrentUserId());
    }

    @GetMapping("/add")
    public String addPage() {
        return "/work/customer/edit";
    }

    @GetMapping("/edit")
    public ModelAndView editPage(@ModelAttribute Customer customer, @RequestParam String id) {
        customer = customerService.findById(id);

        ModelAndView mav = new ModelAndView("/work/customer/edit");
        mav.addObject("customer", customer);
        return mav;
    }

    @PostMapping
    public String save(@ModelAttribute @Valid Customer customer) {
        customer.setUserId(userService.getCurrentUserId());
        customerService.save(customer);

        return RouteUtil.redirectString(URI_HOME);
    }

    @GetMapping("/list")
    public String listPage() {
        return "work/customer/list";
    }
}