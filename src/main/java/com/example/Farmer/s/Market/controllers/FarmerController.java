package com.example.Farmer.s.Market.controllers;

import com.example.Farmer.s.Market.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("market")
public class FarmerController {

    @RequestMapping(value="")
    public String index(Model model, HttpServletRequest request, User user){
        String username = request.getParameter("username");
        model.addAttribute("title", "Farmer's Market");
        model.addAttribute("username", username);


        return "market/index";
    }

    @RequestMapping(value = "goodbye")
    @ResponseBody
    public String goodbye(){
        return "Goodbye";
    }
}

