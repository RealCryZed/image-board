package com.example.imageboard.controller;

import com.example.imageboard.model.Comment;
import com.example.imageboard.model.Post;
import com.example.imageboard.model.User;
import com.example.imageboard.service.CommentService;
import com.example.imageboard.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
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

    @RequestMapping("/login")
    public ModelAndView getLoginPage(ModelAndView modelAndView) {
        log.info("in login request mapping method");
        modelAndView.addObject("sidebarPosts", postService.find10Posts());
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView getRegisterPage(ModelAndView modelAndView) {
        modelAndView.addObject("sidebarPosts", postService.find10Posts());
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("register");
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
