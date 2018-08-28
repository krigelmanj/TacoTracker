package com.example.Farmer.s.Market.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("login")
public class LoginController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayLoginForm(Model model){
        model.addAttribute("title", "Login");
        return "login/login";
    }
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String verifyLogin(Model model){
        model.addAttribute("title", "Login");
        return "login/verify";
    }

}
