package com.jrp.pma.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;

@Controller
public class AppErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
