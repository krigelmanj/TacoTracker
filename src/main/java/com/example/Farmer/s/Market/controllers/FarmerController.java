package com.example.Farmer.s.Market.controllers;

import com.example.Farmer.s.Market.models.User;


import com.montealegreluis.yelpv3.Yelp;
import com.montealegreluis.yelpv3.businesses.Coordinates;
import com.montealegreluis.yelpv3.businesses.SearchResult;
import com.montealegreluis.yelpv3.client.Credentials;
import com.montealegreluis.yelpv3.search.SearchCriteria;
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
        model .addAttribute("username", username);
        Credentials clientId = new Credentials("UsVIhi8B5OqaSawl-d_MIg","GJM3ct05cXXX0WjMcRN1LLxz5kOJAHs0pTD6Eu0Yu3yChpUFijugH_OF7WFzbxeF09TKcbYvRuYDNU9PCmoBcF2ohwG-c9sqTgZ0_jGGCJP4KENg6Tl8TbtnPQiuW3Yx");

        Yelp yelp = new Yelp(clientId);
        yelp.search(SearchCriteria.byLocation("San Antonio"));
        yelp.search(SearchCriteria.byCoordinates(29.426786, -98.489576));
        yelp.search(SearchCriteria.byCoordinates(new Coordinates(29.426786, -98.489576)));
        SearchCriteria criteria = SearchCriteria.byLocation("San Antonio");
        SearchResult result = yelp.search(criteria).searchResult();
        model.addAttribute("businesses", result.businesses );


        return "market/index";
    }

    @RequestMapping(value = "goodbye")
    @ResponseBody
    public String goodbye(){
        return "Goodbye";
    }
}

