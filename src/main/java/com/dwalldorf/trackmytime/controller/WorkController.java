package com.dwalldorf.trackmytime.controller;

import com.dwalldorf.trackmytime.model.Customer;
import com.dwalldorf.trackmytime.model.Project;
import com.dwalldorf.trackmytime.model.WorkEntry;
import com.dwalldorf.trackmytime.service.CustomerService;
import com.dwalldorf.trackmytime.service.ProjectService;
import com.dwalldorf.trackmytime.service.UserService;
import com.dwalldorf.trackmytime.service.WorkEntryService;
import com.dwalldorf.trackmytime.util.RouteUtil;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WorkController {

    private static final String URI_WORK_PREFIX = "/work";

    public static final String URI_WORK_ADD = URI_WORK_PREFIX + "/add";
    public static final String URI_WORK_LIST = URI_WORK_PREFIX + "/list";

    private final UserService userService;

    private final WorkEntryService workEntryService;

    private final CustomerService customerService;

    private final ProjectService projectService;

    @Inject
    public WorkController(
            UserService userService,
            WorkEntryService workEntryService,
            CustomerService customerService,
            ProjectService projectService) {
        this.userService = userService;
        this.workEntryService = workEntryService;
        this.customerService = customerService;
        this.projectService = projectService;
    }

    @ModelAttribute("allEntries")
    public List<WorkEntry> allEntries() {
        return workEntryService.findAllByUser(userService.getCurrentUserId());
    }

    @ModelAttribute("allCustomers")
    public List<Customer> allCustomers() {
        return customerService.findAllByUser(userService.getCurrentUserId());
    }

    @ModelAttribute("allProjects")
    public List<Project> allProjects() {
        return projectService.findAllByUser(userService.getCurrentUserId());
    }

    @GetMapping(URI_WORK_ADD)
    public String addPage(@ModelAttribute("workEntry") WorkEntry workEntry) {
        workEntry.setUserId(userService.getCurrentUserId());
        return "work/edit";
    }

    @GetMapping(URI_WORK_LIST)
    public String listPage() {
        return "work/list";
    }

    @PostMapping(URI_WORK_PREFIX)
    public String add(@ModelAttribute @Valid WorkEntry workEntry) {
        workEntry.setUserId(userService.getCurrentUserId());

        workEntryService.save(workEntry);
        return RouteUtil.redirectString(URI_WORK_LIST);
    }
}