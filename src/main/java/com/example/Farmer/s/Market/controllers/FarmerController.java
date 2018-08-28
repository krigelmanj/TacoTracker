package com.example.Farmer.s.Market.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("market")
public class FarmerController {

    @RequestMapping(value="")
    public String index(Model model){
        model.addAttribute("title", "Farmer's Market");


        return "market/index";
    }

    @RequestMapping(value = "goodbye")
    @ResponseBody
    public String goodbye(){
        return "Goodbye";
    }
}

