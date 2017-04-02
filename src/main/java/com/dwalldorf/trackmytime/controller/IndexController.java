package com.dwalldorf.trackmytime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private static final String ROUTE_PAGE_INDEX = "/";
    private static final String ROUTE_PAGE_HOME = "/home";

    private static final String VIEW_NAME = "/index";

    @GetMapping(ROUTE_PAGE_INDEX)
    public String index() {
        return VIEW_NAME;
    }

    @GetMapping(ROUTE_PAGE_HOME)
    public String home() {
        return VIEW_NAME;
    }
}