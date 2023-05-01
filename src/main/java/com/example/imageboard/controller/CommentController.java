package com.example.imageboard.controller;

import com.example.imageboard.function.NicknameGenerator;
import com.example.imageboard.model.Comment;
import com.example.imageboard.model.Post;
import com.example.imageboard.service.CommentService;
import com.example.imageboard.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

@Controller
@Slf4j
public class CommentController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/title-page/{id}/add-comment")
    public ModelAndView addComment(ModelAndView modelAndView, @PathVariable Long id, @Valid Comment comment) {
        Post post = postService.findById(id);
        comment.setPost(post);
        comment.setAuthor(NicknameGenerator.generateNickname());
        log.info("Generated nickname: " + comment.getAuthor());
        commentService.create(comment);

        modelAndView.addObject("comment", new Comment());
        modelAndView.addObject("comments", commentService.findAll());
        modelAndView.setViewName("/title-page/" + id);
        log.info("Added new Comment " + comment);

        return modelAndView;
    }
}
