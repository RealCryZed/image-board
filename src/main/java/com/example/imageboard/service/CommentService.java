package com.example.imageboard.service;

import com.example.imageboard.function.ImageProcessor;
import com.example.imageboard.function.WordFiltering;
import com.example.imageboard.model.Comment;
import com.example.imageboard.model.Post;
import com.example.imageboard.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired PostService postService;

    @Autowired
    private NicknameGeneratorService nicknameGeneratorService;

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Comment findById(Long id) {
        return commentRepository.getById(id);
    }

    public List<Comment> findAllCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findAllByPost_id(postId);

        for (Comment comment : comments) {
            if (comment.getImage() != null) comment.setBase64Image(ImageProcessor.getBase64Image(comment.getImage()));
        }

        return commentRepository.findAllByPost_id(postId);
    }

    public Comment create(Comment comment, Long postId) {
        if (comment.getContent().isEmpty() && comment.getFile().isEmpty()) return null;
        Post post = postService.findById(postId);
        Comment tempComment = new Comment();

        if (comment.getReplyToNickname() != null) {
            tempComment.setReplyToNickname(comment.getReplyToNickname().substring(0, comment.getReplyToNickname().length() - 1));
            tempComment.setContent(comment.getReplyToNickname() + ", " + comment.getContent());
        } else {
            tempComment.setContent(comment.getContent());
        }

        if (comment.getFile() != null) {
            try {
                tempComment.setImage(comment.getFile().getBytes());
                tempComment.setBase64Image(ImageProcessor.getBase64Image(comment.getFile().getBytes()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (WordFiltering.containsBanWord(comment.getContent())) return null;

        tempComment.setPost(post);
        tempComment.setPost_id(post.getId());
        tempComment.setAuthor(nicknameGeneratorService.generateNickname());
        return commentRepository.save(tempComment);
    }

    public Comment update(Long id, Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}
