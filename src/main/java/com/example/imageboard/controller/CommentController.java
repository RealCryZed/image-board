package com.example.imageboard.controller;

import com.example.imageboard.model.Comment;
import com.example.imageboard.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/title-page/{id}")
    public ModelAndView addComment(ModelAndView modelAndView, @PathVariable Long id,
                                   @ModelAttribute Comment comment, @RequestParam("imageFile") MultipartFile file) {
        comment.setFile(file);
        commentService.create(comment, id);

        modelAndView.setViewName("redirect:/title-page/" + id);
        return modelAndView;
    }

    @PostMapping("/home/title-page/{id}")
    public ModelAndView addCommentFromHomePage(ModelAndView modelAndView,
                                               @PathVariable Long id, @ModelAttribute Comment comment,
                                               @RequestParam("imageFile") MultipartFile file) {
        comment.setFile(file);
        commentService.create(comment, id);

        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

    @PostMapping("/title-page/{id}/reply/{nickname}")
    public ModelAndView addCommentReply(ModelAndView modelAndView, @PathVariable Long id, @ModelAttribute Comment comment,
                                        @PathVariable String nickname, @RequestParam("imageFile") MultipartFile file) {
        comment.setFile(file);
        comment.setReplyToNickname(nickname);
        commentService.create(comment, id);

        modelAndView.setViewName("redirect:/title-page/" + id);
        return modelAndView;
    }

    @PostMapping("/home/title-page/{id}/reply/{nickname}")
    public ModelAndView addCommentReplyFromHomePage(ModelAndView modelAndView, @PathVariable Long id,
                                                    @ModelAttribute Comment comment, @PathVariable String nickname,
                                                    @RequestParam("imageFile") MultipartFile file) {
        comment.setFile(file);
        comment.setReplyToNickname(nickname);
        commentService.create(comment, id);

        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
