package com.jrp.pma.controllers;

import com.jrp.pma.dao.IUserAccountRepository;
import com.jrp.pma.entities.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecurityController {

    @Autowired
    IUserAccountRepository accountRepo;

    @Autowired // this Bean is defined in our WebConfig class
    BCryptPasswordEncoder bCryptEncoder;

    // display the registration form
    @GetMapping("/register")
    public String register (Model model) {

        UserAccount userAccount = new UserAccount(); // this is why we created an empty constructor, the empty Object will be sent to the form
        model.addAttribute("userAccount", userAccount);

        return "security/register";
    }

    // save the data
    @PostMapping("/register/save")
    public String saveUser(Model model, UserAccount user) { // the filled 'user' Object will be sent here from the previous function

        user.setPassword( bCryptEncoder.encode(user.getPassword()) ); // here we can invoke the Encoder on the password that passed by the form, before it will saved to the DB, getPassword - the password from the form, before encryption

        // save the user to the DB (we not using a special 'Service' here as we did before, to save time)
        accountRepo.save(user);

        return "redirect:/"; // redirect to the main Dashboard

    }
}
