package com.example.imageboard.controller;

import com.example.imageboard.model.Comment;
import com.example.imageboard.model.Report;
import com.example.imageboard.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping("/report/{commentId}")
    public ModelAndView reportComment(ModelAndView modelAndView, @PathVariable Long commentId) {
        reportService.report(commentId);

        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
}
