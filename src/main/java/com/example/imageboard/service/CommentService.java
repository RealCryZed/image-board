package com.example.imageboard.service;

import com.example.imageboard.model.Comment;
import com.example.imageboard.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Comment findById(Long id) {
        return commentRepository.getById(id);
    }

    public Comment create(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment update(Long id, Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}
