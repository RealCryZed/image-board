package com.example.imageboard.repository;

import com.example.imageboard.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment findCommentByAuthor(String author);

    List<Comment> findAllByPost_id(Long postId);
}
