package com.dwalldorf.trackmytime.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {

    @Value("${app.name}")
    private String name;

    @Value("${app.description}")
    private String description;

    @Value("${app.version}")
    private String version;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getVersion() {
        return version;
    }
}