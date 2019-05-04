package ir.itsurena.saba.security.controller;

import ir.itsurena.saba.security.dao.UMSService;
import ir.itsurena.saba.security.dao.UserDao;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


import java.security.Principal;

@RestController
public class WebController {


    @Autowired
    UserDao cache;

    @Autowired
    UMSService umsService;


    @GetMapping(path = "/")
    public String index(Authentication authentication) {

        return "external";
    }

    @GetMapping(path = "/customers")
    public String customers(Principal principal, Authentication authentication, Model model) {
        addCustomers();
        model.addAttribute("customers", "");
        return "customers";
    }

    // add customers for demonstration
    public void addCustomers() {

    }


}
