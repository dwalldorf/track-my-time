package com.dwalldorf.trackmytime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    private static final String VIEW_NAME = "index";

    @GetMapping
    public String index() {
        return VIEW_NAME;
    }

    @GetMapping("/home")
    public String home() {
        return VIEW_NAME;
    }
}