package com.example.imageboard.service;

import com.example.imageboard.model.Comment;
import com.example.imageboard.model.Post;
import com.example.imageboard.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return commentRepository.findAllByPost_id(postId);
    }

    public Comment create(Comment comment, Long postId) {
        Post post = postService.findById(postId);
        Comment tempComment = new Comment();

        if (!comment.getReplyToNickname().isEmpty()) {
            tempComment.setReplyToNickname(comment.getReplyToNickname().substring(0, comment.getReplyToNickname().length() - 1));
            tempComment.setContent(comment.getReplyToNickname() + ", " + comment.getContent());
        } else {
            tempComment.setContent(comment.getContent());
        }

        tempComment.setPost(post);
        tempComment.setImage(comment.getImage());
        tempComment.setPost_id(post.getId());
        tempComment.setAuthor(nicknameGeneratorService.generateNickname());
        log.info("Generated nickname: " + tempComment.getAuthor());
        log.info("Added new Comment " + tempComment);
        return commentRepository.save(tempComment);
    }

    public Comment update(Long id, Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}
