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
import java.util.stream.Collectors;

@Controller
public class PageController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public ModelAndView getMainPage(ModelAndView modelAndView) {
        List<Post> allPosts = postService.findAll();

        modelAndView.addObject("futureComment", new Comment());
        modelAndView.addObject("posts", allPosts);
        modelAndView.addObject("sidebarPosts", allPosts.stream().limit(10).collect(Collectors.toList()));
        modelAndView.setViewName("main-page");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage(ModelAndView modelAndView) {
        modelAndView.addObject("sidebarPosts", postService.find10Posts());
        modelAndView.setViewName("entrance-page");
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView getRegisterPage(ModelAndView modelAndView) {
        modelAndView.addObject("sidebarPosts", postService.find10Posts());
        modelAndView.setViewName("create-account-page");
        return modelAndView;
    }

    @GetMapping("/title-page/{id}")
    public ModelAndView getRegisterPage(ModelAndView modelAndView, @PathVariable Long id) {
        modelAndView.addObject("futureComment", new Comment());
        modelAndView.addObject("allComments", commentService.findAllCommentsByPostId(id));
        modelAndView.addObject("post", postService.findById(id));
        modelAndView.addObject("sidebarPosts", postService.find10Posts());
        modelAndView.setViewName("title-page");

        return modelAndView;
    }
}
