package com.jrp.pma.controllers;

import com.jrp.pma.entities.UserAccount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/register")
    public String register (Model model) {

        UserAccount userAccount = new UserAccount(); // this is why we created an empty constructor, the empty Object will be sent to the form


    }
}
