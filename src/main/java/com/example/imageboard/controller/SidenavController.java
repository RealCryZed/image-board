package com.example.imageboard.controller;

import com.example.imageboard.model.Post;
import com.example.imageboard.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("sidenav")
public class SidenavController {

    private PostService postService;

    @Autowired
    public SidenavController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public ModelAndView getAllPosts(ModelAndView model) {
        List<Post> posts = postService.findAll().stream().limit(5).collect(Collectors.toList());
        log.info("got 5 posts to sidebar");

        model.addObject("posts", posts);
        model.setViewName("sidenav");
        return model;
    }
}
