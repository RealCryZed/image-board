package com.example.imageboard.service;

import com.example.imageboard.model.Comment;
import com.example.imageboard.model.Report;
import com.example.imageboard.repository.ReportRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private CommentService commentService;

    public Report report(Long commentId) {
        Comment comment = commentService.findById(commentId);
        Report report = new Report();
        report.setActive(true);
        report.setComment(comment);
        log.info("Reported comment: " + report.getComment());
        return reportRepository.save(report);
    }
}
