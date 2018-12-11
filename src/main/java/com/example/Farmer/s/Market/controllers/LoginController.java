package com.example.Farmer.s.Market.controllers;


import com.example.Farmer.s.Market.models.User;
import com.example.Farmer.s.Market.models.data.UserDao;
import com.example.Farmer.s.Market.models.data.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("login")
public class LoginController {
    @Autowired
    private UserDao userDao;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayLoginForm(Model model) {
        model.addAttribute("title", "Login");
        model.addAttribute(new User());

        return "login/login";

    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String displayLoginVerified(Model model, User user, BindingResult result , HttpServletRequest request) {
        model.addAttribute("title", "Login");
        User dupeUser = userDao.findTopByName(user.getName());

        if(user.getName() == ""){
            result.rejectValue("name", "error.user.name2", "Please enter a username.");
            return  "login/login";

        }
        if(user.getPassword() == ""){
            result.rejectValue("password", "error.user.password2", "Please enter a password.");
            return  "login/login";

        }
        if (dupeUser != null) {
            if (user.getName().equals(dupeUser.getName())) {
                if (user.getPassword().equals(dupeUser.getPassword())) {
                    HttpSession session = request.getSession(true);

                    session.setAttribute("userName", request.getParameter(user.getName()));
                    session.setAttribute("password", request.getParameter("password"));
                    model.addAttribute("username", request.getParameter("username"));

                    return "login/verify";
                } else {
                    result.rejectValue("password", "error.user.password", "That Username and password combination is not recognized.");

                    return "login/login";
                }
            }

        } else {
            result.rejectValue("name", "error.user.name", "That Username does not exist.");
            return "login/login";

        }
        return  "login/login";
    }



    @RequestMapping(value = "sign-up", method = RequestMethod.GET)
    public String signUp( Model model, User newUser ){
        model.addAttribute("title", "Sign-up");
        model.addAttribute(new User());
        return "login/sign-up";
    }

    @RequestMapping(value = "sign-up", method = RequestMethod.POST)

    public String verifySignUp(@ModelAttribute @Valid User newUser, BindingResult result, Errors errors,

                               Model model){

        UserValidator userValidator = new UserValidator();
        userValidator.validate(newUser, result);
        User dupeUser = userDao.findTopByName(newUser.getName());

        if(dupeUser != null) {
            if (newUser.getName().equals(dupeUser.getName())) {
                result.rejectValue("name", "error.user.name", "That Username is already registered.");
            }
        }
        if (result.hasErrors()){

            model.addAttribute("title", "Sign-up");
            return "login/sign-up";
        }
        else{
        model.addAttribute("title", "Sign-up");
        userDao.save(newUser);

        return "login/verifySignUp";
        }

    }


}
