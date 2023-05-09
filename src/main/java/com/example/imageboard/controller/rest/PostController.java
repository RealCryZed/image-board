package com.example.imageboard.controller.rest;

import com.example.imageboard.service.PostService;
import com.example.imageboard.model.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/posts")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @GetMapping
    private List<Post> findAllPosts() {
        List<Post> allPosts = postService.findAll();
        log.info("Got all posts");
        return allPosts;
    }

    @GetMapping("/{id}")
    private Post findPostById(@PathVariable Long id) {
        Post post = postService.findById(id);
        log.info("Got a post with id={}", id);
        return post;
    }

    @GetMapping("/{article}")
    private Post findPostByArticle(@PathVariable String article) {
        Post post = postService.findPostByArticle(article);
        log.info("Got a post with article={}", article);
        return post;
    }

    @PostMapping("/upload")
    public Post create(@ModelAttribute Post post) {
        Post newPost = postService.create(post);
        log.info("Uploaded new post");
        return newPost;
    }
}
