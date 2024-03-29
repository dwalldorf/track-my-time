package com.dwalldorf.trackmytime.controller;

import com.dwalldorf.trackmytime.model.Project;
import com.dwalldorf.trackmytime.service.ProjectService;
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
public class WorkProjectController {

    private static final String ROUTE_PREFIX = "/work/project";

    private static final String ROUTE_PAGE_LIST = ROUTE_PREFIX + "/list";
    private static final String ROUTE_PAGE_ADD = ROUTE_PREFIX + "/add";
    private static final String ROUTE_PAGE_EDIT = ROUTE_PREFIX + "/{id}/edit";
    private static final String ROUTE_ACTION_DELETE = ROUTE_PREFIX + "/{id}/delete";

    private final static String VIEW_PREFIX = "/work/project/";
    private final static String VIEW_EDIT = VIEW_PREFIX + "edit";
    private final static String VIEW_LIST = VIEW_PREFIX + "list";

    private final ProjectService projectService;

    private final UserService userService;

    @Inject
    public WorkProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
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
    public ModelAndView addPage() {
        ModelAndView mav = new ModelAndView(VIEW_EDIT);
        mav.addObject("project", new Project());
        return mav;
    }

    @GetMapping(ROUTE_PAGE_EDIT)
    public ModelAndView editPage(@PathVariable String id) {
        Project project = projectService.findById(id);

        userService.verifyOwner(project);

        ModelAndView mav = new ModelAndView(VIEW_EDIT);
        mav.addObject("project", project);
        return mav;
    }

    @PostMapping(ROUTE_PREFIX)
    public String save(@ModelAttribute Project project) {
        if (project.getId() == null) {
            project.setUserId(userService.getCurrentUserId());
        } else {
            Project persistedProject = projectService.findById(project.getId());
            userService.verifyOwner(persistedProject);
        }

        projectService.save(project);

        return RouteUtil.redirectString(ROUTE_PAGE_LIST);
    }

    @GetMapping(ROUTE_ACTION_DELETE)
    public String delete(@PathVariable String id) {
        Project project = projectService.findById(id);

        userService.verifyOwner(project);
        projectService.delete(project);

        return RouteUtil.redirectString(ROUTE_PAGE_LIST);
    }
}