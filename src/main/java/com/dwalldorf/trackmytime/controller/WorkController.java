package com.dwalldorf.trackmytime.controller;

import com.dwalldorf.trackmytime.forms.user.WorkEntryForm;
import com.dwalldorf.trackmytime.model.Customer;
import com.dwalldorf.trackmytime.model.Project;
import com.dwalldorf.trackmytime.model.WorkEntry;
import com.dwalldorf.trackmytime.service.UserService;
import com.dwalldorf.trackmytime.service.WorkEntryService;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/work")
public class WorkController {

    private final UserService userService;

    private final WorkEntryService workEntryService;

    @Inject
    public WorkController(UserService userService, WorkEntryService workEntryService) {
        this.userService = userService;
        this.workEntryService = workEntryService;
    }

    @ModelAttribute("allEntries")
    public List<WorkEntry> allEntries() {
        return workEntryService.findAllByUser(userService.getCurrentUserId());
    }

    @ModelAttribute("allCustomers")
    public List<Customer> allCustomers() {
        return workEntryService.findAllCustomersByUser(userService.getCurrentUserId());
    }

    @ModelAttribute("allProjects")
    public List<Project> allProjects() {
        return workEntryService.findAllProjectsByUser(userService.getCurrentUserId());
    }

    @GetMapping("/add")
    public String addPage(@ModelAttribute("workEntryForm") WorkEntryForm workEntryForm) {
        workEntryForm = new WorkEntryForm();
        workEntryForm.setCustomer("Springer Nature");
        workEntryForm.setComment("test");
        return "work/edit";
    }

    @GetMapping("/list")
    public String listPage() {
        return "work/list";
    }

    @PostMapping
    public String add(@ModelAttribute @Valid WorkEntryForm workEntryForm) {
        WorkEntry workEntry = new WorkEntry()
                .setCustomerId(null)
                .setProjectId(null)
                .setComment(workEntryForm.getComment())
                .setStart(workEntryForm.getStart())
                .setStop(workEntryForm.getStop());

        workEntryService.save(workEntry);
        return listPage();
    }
}