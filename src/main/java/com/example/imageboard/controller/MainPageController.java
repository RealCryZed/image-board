package com.example.imageboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainPageController {

    @GetMapping("/")
    public ModelAndView getMainPage(ModelAndView modelAndView) {
        modelAndView.setViewName("main-page");
        return modelAndView;
    }
}
