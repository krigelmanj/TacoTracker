package com.example.Farmer.s.Market.controllers;

import com.example.Farmer.s.Market.models.TrackerBusiness;
import com.example.Farmer.s.Market.models.User;


import com.example.Farmer.s.Market.models.City;
import com.example.Farmer.s.Market.models.data.TrackerBusinessDao;
import com.montealegreluis.yelpv3.Yelp;
import com.montealegreluis.yelpv3.businesses.Business;
import com.montealegreluis.yelpv3.businesses.Coordinates;
import com.montealegreluis.yelpv3.businesses.SearchResult;
import com.montealegreluis.yelpv3.client.Credentials;
import com.montealegreluis.yelpv3.search.Limit;
import com.montealegreluis.yelpv3.search.SearchCriteria;
import com.montealegreluis.yelpv3.search.SortingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.montealegreluis.yelpv3.search.Limit.of;


@Controller
@RequestMapping("market")
public class FarmerController {
    @Autowired
    private TrackerBusinessDao trackerBusinessDao;

    @RequestMapping(value="", method = RequestMethod.GET)
    public String locationRequest(Model model, HttpServletRequest request, User user){
        String username = request.getParameter("username");
        model.addAttribute("title", "Taco Tracker");
        model.addAttribute("username", username);
        model.addAttribute(new City());

        return "market/index";
    }


    @RequestMapping(value="", method = RequestMethod.POST)
    public String index(Model model, HttpServletRequest request, User user, @ModelAttribute @Valid City city){
        HttpSession session = request.getSession(false);
        if (session == null) {
            model.addAttribute("title", "Please login before searching.");

            return "redirect:/login";
        }
        String username = (String) session.getAttribute("username");

        model.addAttribute("title", "Taco Tracker");
        model.addAttribute("username", username);
        Credentials clientId = new Credentials("UsVIhi8B5OqaSawl-d_MIg","GJM3ct05cXXX0WjMcRN1LLxz5kOJAHs0pTD6Eu0Yu3yChpUFijugH_OF7WFzbxeF09TKcbYvRuYDNU9PCmoBcF2ohwG-c9sqTgZ0_jGGCJP4KENg6Tl8TbtnPQiuW3Yx");

        Yelp yelp = new Yelp(clientId);
        yelp.search(SearchCriteria.byLocation(city.getName()));
        yelp.search(SearchCriteria.byCoordinates(29.426786, -98.489576));
        yelp.search(SearchCriteria.byCoordinates(new Coordinates(29.426786, -98.489576)));
        SearchCriteria criteria = SearchCriteria.byLocation(city.getName());
        criteria.withTerm("restaurants");
        criteria.inCategories("mexican");
        criteria.sortBy(SortingMode.REVIEW_COUNT);

        Limit limit = of(50);

        criteria.limit(limit);


        SearchResult result = yelp.search(criteria).searchResult();
        for (Business business: result.businesses){
            TrackerBusiness trackerBusiness = new TrackerBusiness(business);
            trackerBusinessDao.save(trackerBusiness);

        }
        model.addAttribute("businesses", result.businesses );


        return "market/index";
    }

    @RequestMapping(value = "goodbye")
    @ResponseBody
    public String goodbye(){
        return "Goodbye";
    }
}

