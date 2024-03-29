package com.dwalldorf.trackmytime.controller;

import com.dwalldorf.trackmytime.model.Customer;
import com.dwalldorf.trackmytime.service.CustomerService;
import com.dwalldorf.trackmytime.service.UserService;
import com.dwalldorf.trackmytime.util.RouteUtil;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WorkCustomerController {

    private static final String ROUTE_PREFIX = "/work/customer";

    private static final String ROUTE_PAGE_LIST = ROUTE_PREFIX + "/list";
    private static final String ROUTE_PAGE_ADD = ROUTE_PREFIX + "/add";
    private static final String ROUTE_PAGE_EDIT = ROUTE_PREFIX + "/{id}/edit";
    private static final String ROUTE_ACTION_DELETE = ROUTE_PREFIX + "/{id}/delete";

    private final static String VIEW_PREFIX = "/work/customer/";
    private final static String VIEW_EDIT = VIEW_PREFIX + "edit";
    private final static String VIEW_LIST = VIEW_PREFIX + "list";

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

    @GetMapping(ROUTE_PREFIX)
    public String indexRedirect() {
        return RouteUtil.redirectString(ROUTE_PAGE_LIST);
    }

    @GetMapping(ROUTE_PAGE_LIST)
    public String listPage() {
        return VIEW_LIST;
    }

    @GetMapping(ROUTE_PAGE_ADD)
    public ModelAndView addPage() {
        return new ModelAndView(VIEW_EDIT)
                .addObject("customer", new Customer());
    }

    @GetMapping(ROUTE_PAGE_EDIT)
    public ModelAndView editPage(@PathVariable String id) {
        Customer customer = customerService.findById(id);

        userService.verifyOwner(customer);

        ModelAndView mav = new ModelAndView(VIEW_EDIT);
        mav.addObject("customer", customer);
        return mav;
    }

    @PostMapping(ROUTE_PREFIX)
    public String save(@ModelAttribute Customer customer) {
        if (customer.getId() == null) {
            customer.setUserId(userService.getCurrentUserId());
        } else {
            Customer persistedCustomer = customerService.findById(customer.getId());
            userService.verifyOwner(persistedCustomer);
        }
        customerService.save(customer);

        return RouteUtil.redirectString(ROUTE_PAGE_LIST);
    }

    @GetMapping(ROUTE_ACTION_DELETE)
    public String delete(@PathVariable String id) {
        Customer customer = customerService.findById(id);
        userService.verifyOwner(customer);

        customerService.delete(customer);

        return RouteUtil.redirectString(ROUTE_PAGE_LIST);
    }
}