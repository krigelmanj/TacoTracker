package com.example.Farmer.s.Market.models.data;


import com.example.Farmer.s.Market.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {


    @Autowired
    private UserDao userDao;

    @Override
    public boolean supports(Class clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        if(user.getName() == null) {
            errors.rejectValue("name", "Please enter a username");
        }

    }

}