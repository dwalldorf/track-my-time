package com.dwalldorf.trackmytime.rest.controller;

import com.dwalldorf.trackmytime.config.ApplicationProperties;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(VersionController.BASE_URI)
public class VersionController {

    public static final String BASE_URI = "/version";

    private final ApplicationProperties applicationProperties;

    @Inject
    public VersionController(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @GetMapping
    public String getVersion() {
        return applicationProperties.getVersion();
    }
}