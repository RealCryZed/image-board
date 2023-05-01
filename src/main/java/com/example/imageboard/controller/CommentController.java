package com.example.imageboard.controller;

import com.example.imageboard.model.Comment;
import com.example.imageboard.model.Post;
import com.example.imageboard.service.CommentService;
import com.example.imageboard.service.NicknameGeneratorService;
import com.example.imageboard.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

@Controller
@Slf4j
public class CommentController {

    @Autowired
    private NicknameGeneratorService nicknameGeneratorService;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/title-page/{id}")
    public ModelAndView addComment(ModelAndView modelAndView, @PathVariable Long id, @ModelAttribute Comment comment) {
        Post post = postService.findById(id);

        comment.setPost(post);
        comment.setAuthor(nicknameGeneratorService.generateNickname());
        log.info("Generated nickname: " + comment.getAuthor());
        commentService.create(comment);
        log.info("Added new Comment " + comment);

        modelAndView.setViewName("redirect:/title-page/" + id);

        return modelAndView;
    }
}
