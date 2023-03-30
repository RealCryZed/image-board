package com.example.imageboard.repository;

import com.example.imageboard.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Post findPostByPostName(String postName);
}
