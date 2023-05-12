package com.example.imageboard.controller;

import com.example.imageboard.model.User;
import com.example.imageboard.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ModelAndView createNewUser (ModelAndView modelAndView,
                                       @Valid User user, BindingResult bindingResult) {
        log.info("Opened register post request...");
        User userExists = userService.findUserByUsername(user.getUsername());
        log.info(userExists + " is user, that found in DB");
        if (userExists != null) {
            log.info("userExists is not null, setting bindingResult 'username'");
            bindingResult.rejectValue("username", "Username was already registered");
        }
        if (bindingResult.hasErrors()) {
            log.info("bindingResult has errors");
            modelAndView.setViewName("create-account-page");
        } else {
            log.info("registering user");
            userService.createUser(user);
            log.info("user registered");
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            log.info("sucecssMessage was set, going to login page");
            modelAndView.setViewName("redirect:/login");
        }

        return modelAndView;
    }
}
