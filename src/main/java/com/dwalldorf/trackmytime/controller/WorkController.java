package com.dwalldorf.trackmytime.controller;

import com.dwalldorf.trackmytime.model.WorkEntry;
import com.dwalldorf.trackmytime.service.WorkEntryService;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/work")
public class WorkController {

    private final WorkEntryService workEntryService;

    @Inject
    public WorkController(WorkEntryService workEntryService) {
        this.workEntryService = workEntryService;
    }

    @GetMapping("/add")
    public String addPage(@ModelAttribute WorkEntry workEntry) {
        return "work/edit";
    }
}