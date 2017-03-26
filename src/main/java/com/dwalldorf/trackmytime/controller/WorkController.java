package com.dwalldorf.trackmytime.controller;

import com.dwalldorf.trackmytime.exception.ResourceConflictException;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WorkController {

    private static final String URI_WORK_PREFIX = "/work";

    public static final String URI_WORK_ADD = URI_WORK_PREFIX + "/add";
    public static final String URI_WORK_LIST = URI_WORK_PREFIX + "/list";
    public static final String URI_WORK_EDIT = URI_WORK_PREFIX + "/edit";
    public static final String URI_WORK_DELETE = URI_WORK_PREFIX + "/delete";

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

    @GetMapping(URI_WORK_EDIT)
    public ModelAndView editPage(@PathVariable String id) {
        WorkEntry workEntry = workEntryService.findById(id);

        ModelAndView mav = new ModelAndView("/work/edit");
        mav.addObject("workEntry", workEntry);
        return mav;
    }

    @PostMapping(URI_WORK_PREFIX)
    public String save(@ModelAttribute @Valid WorkEntry workEntry) {
        String currentUserId = userService.getCurrentUserId();

        if (workEntry.getId() == null) {
            workEntry.setUserId(currentUserId);
        } else {
            if (!currentUserId.equals(workEntry.getUserId())) {
                throwResourceConflict(currentUserId, workEntry);
            }
        }
        workEntryService.save(workEntry);

        return RouteUtil.redirectString(URI_WORK_LIST);
    }

    @GetMapping(URI_WORK_DELETE)
    public String delete(@PathVariable String id) {
        String currentUserId = userService.getCurrentUserId();
        WorkEntry workEntry = workEntryService.findById(id);

        if (!currentUserId.equals(workEntry.getUserId())) {
            throwResourceConflict(currentUserId, workEntry);
        }

        workEntryService.delete(workEntry);
        return RouteUtil.redirectString(URI_WORK_LIST);
    }

    private void throwResourceConflict(String currentUserId, WorkEntry workEntry) {
        throw new ResourceConflictException(
                String.format("User %s tried to modify work entry %s but belongs to user %s",
                        currentUserId,
                        workEntry.getId(),
                        workEntry.getUserId())
        );
    }
}