package com.dwalldorf.trackmytime.controller;

import com.dwalldorf.trackmytime.util.RouteUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    public static final String ROUTE_PAGE_INDEX = "/";

    @GetMapping(ROUTE_PAGE_INDEX)
    public String index() {
        return RouteUtil.redirectString(WorkController.ROUTE_PAGE_LIST);
    }
}