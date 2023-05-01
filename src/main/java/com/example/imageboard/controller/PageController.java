package com.example.imageboard.controller;

import com.example.imageboard.model.Comment;
import com.example.imageboard.model.Post;
import com.example.imageboard.service.CommentService;
import com.example.imageboard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public ModelAndView getMainPage(ModelAndView modelAndView) {
        Comment futureComment = new Comment();

        modelAndView.addObject("futureComment", futureComment);
        modelAndView.addObject("posts", postService.findAll());
        modelAndView.setViewName("main-page");
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
        Post post = postService.findById(id);
        Comment futureComment = new Comment();

        modelAndView.addObject("futureComment", futureComment);
        modelAndView.addObject("allComments", post.getComments());
        modelAndView.addObject("post", post);
        modelAndView.setViewName("title-page");

        return modelAndView;
    }
}
