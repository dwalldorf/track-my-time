package com.dwalldorf.trackmytime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    private static final String URI_HOME = "/home";

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping(URI_HOME)
    public String home() {
        return index();
    }
}