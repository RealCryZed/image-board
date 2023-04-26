package com.example.imageboard.service;

import com.example.imageboard.function.ImageProcessor;
import com.example.imageboard.model.Post;
import com.example.imageboard.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> findAll() {
        List<Post> posts = postRepository.findAll();
        for (Post post : posts) {
            post.setBase64Image(ImageProcessor.getBase64Image(post.getImage()));
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
