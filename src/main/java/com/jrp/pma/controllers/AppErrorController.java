package com.jrp.pma.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AppErrorController implements ErrorController {

    // define the EndPoint handler for /error (from below)
    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "errorpages/error-404";
            }
            else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "errorpages/error-500";
            }
            else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "errorpages/error-403";
            }

        }

        // by default return (the fallback)
        return "errorpages/error";
    }

    @Override // override the only one function in ErrorController
    public String getErrorPath() {
        return "/error";
    }
}
