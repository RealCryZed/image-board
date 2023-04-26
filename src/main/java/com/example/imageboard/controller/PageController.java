package com.example.imageboard.controller;

import com.example.imageboard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageController {

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public ModelAndView getMainPage(ModelAndView modelAndView) {

        modelAndView.setViewName("main-page");
        modelAndView.addObject("posts", postService.findAll());
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage(ModelAndView modelAndView) {
        modelAndView.setViewName("entrance-page");
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView getRegisterPage(ModelAndView modelAndView) {
        modelAndView.setViewName("create-account-page");
        return modelAndView;
    }

    @GetMapping("/title-page/{id}")
    public ModelAndView getRegisterPage(ModelAndView modelAndView, @PathVariable Long id) {
        modelAndView.setViewName("title-page");

        modelAndView.addObject("post", postService.findById(id));

        return modelAndView;
    }
}
