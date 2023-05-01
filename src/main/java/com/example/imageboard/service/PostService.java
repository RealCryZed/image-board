package com.example.imageboard.service;

import com.example.imageboard.function.ImageProcessor;
import com.example.imageboard.model.Comment;
import com.example.imageboard.model.Post;
import com.example.imageboard.repository.CommentRepository;
import com.example.imageboard.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    public List<Post> findAll() {
        List<Post> posts = postRepository.findAll();
        for (Post post : posts) {
            post.setBase64Image(ImageProcessor.getBase64Image(post.getImage()));
            List<Comment> list = commentRepository.findTop3ByPost_IdOrderByIdDesc(post.getId());

            for (int i = 0, j = list.size() - 1; i < j; i++) {
                list.add(i, list.remove(j));
            }
            post.setComments(list);
        }
        return posts;
    }

    @Transactional
    public Post findById(Long postId) {
        Post foundPost = postRepository.getById(postId);
        foundPost.setBase64Image(ImageProcessor.getBase64Image(foundPost.getImage()));
        return foundPost;
    }

    public Post findPostByArticle(String article) {
        return postRepository.findPostByArticle(article);
    }

    public Post create(Post post) {
        return postRepository.save(post);
    }

    public Post update(Long postId, Post post) {
        return postRepository.save(post);
    }

    public void deleteById(Long postId) {
        postRepository.deleteById(postId);
    }
}
