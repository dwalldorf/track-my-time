package com.dwalldorf.trackmytime.controller;

import static com.dwalldorf.trackmytime.model.WorkEntrySource.USER;

import com.dwalldorf.trackmytime.forms.work.WorkEntryForm;
import com.dwalldorf.trackmytime.model.Customer;
import com.dwalldorf.trackmytime.model.Project;
import com.dwalldorf.trackmytime.model.WorkEntry;
import com.dwalldorf.trackmytime.service.CustomerService;
import com.dwalldorf.trackmytime.service.ProjectService;
import com.dwalldorf.trackmytime.service.UserService;
import com.dwalldorf.trackmytime.service.WorkEntryService;
import com.dwalldorf.trackmytime.util.RouteUtil;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WorkController {

    private static final String ROUTE_PREFIX = "/work";

    private static final String ROUTE_PAGE_ADD = ROUTE_PREFIX + "/add";
    public static final String ROUTE_PAGE_LIST = ROUTE_PREFIX + "/list";
    private static final String ROUTE_PAGE_EDIT = ROUTE_PREFIX + "/{id}/edit";
    private static final String ROUTE_ACTION_DELETE = ROUTE_PREFIX + "/{id}/delete";

    private static final String VIEW_PREFIX = "/work/";
    private static final String VIEW_LIST = VIEW_PREFIX + "list";
    private static final String VIEW_EDIT = VIEW_PREFIX + "edit";

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
    public List<WorkEntryForm> allEntries() {
        List<WorkEntry> all = workEntryService.findAllByUser(userService.getCurrentUserId());
        List<WorkEntryForm> result = new ArrayList<>();

        all.forEach(entry -> result.add(WorkEntryForm.fromWorkEntry(entry)));
        return result;
    }

    @ModelAttribute("allCustomers")
    public List<Customer> allCustomers() {
        return customerService.findAllByUser(userService.getCurrentUserId());
    }

    @ModelAttribute("allProjects")
    public List<Project> allProjects() {
        return projectService.findAllByUser(userService.getCurrentUserId());
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
    public String addPage(@ModelAttribute("workEntryForm") WorkEntryForm workEntryForm) {
        workEntryForm.setUserId(userService.getCurrentUserId());
        return VIEW_EDIT;
    }

    @GetMapping(ROUTE_PAGE_EDIT)
    public ModelAndView editPage(@PathVariable String id) {
        WorkEntry workEntry = workEntryService.findById(id);

        // don't even display if it doesn't belong to current user
        userService.verifyOwner(workEntry);

        ModelAndView mav = new ModelAndView(VIEW_EDIT);
        mav.addObject("workEntryForm", WorkEntryForm.fromWorkEntry(workEntry));
        return mav;
    }

    @PostMapping(ROUTE_PREFIX)
    public String save(@ModelAttribute WorkEntryForm workEntryForm) {
        WorkEntry workEntry = workEntryForm.toWorkEntry();
        if (workEntry.getId() == null) {
            workEntry.setUserId(userService.getCurrentUserId())
                     .setSource(USER);
        } else { // update an entry
            WorkEntry persistedEntry = workEntryService.findById(workEntry.getId());
            userService.verifyOwner(persistedEntry);

            persistedEntry.setCustomerId(workEntry.getCustomerId())
                          .setProjectId(workEntry.getProjectId())
                          .setComment(workEntry.getComment())
                          .setStart(workEntry.getStart())
                          .setStop(workEntry.getStop());

            workEntry = persistedEntry;
        }

        workEntryService.save(workEntry);

        return RouteUtil.redirectString(ROUTE_PAGE_LIST);
    }

    @GetMapping(ROUTE_ACTION_DELETE)
    public String delete(@PathVariable String id) {
        WorkEntry workEntry = workEntryService.findById(id);

        userService.verifyOwner(workEntry);

        workEntryService.delete(workEntry);
        return RouteUtil.redirectString(ROUTE_PAGE_LIST);
    }
}