package com.example.imageboard.mapper;

import com.example.imageboard.dto.post.CreatePostDto;
import com.example.imageboard.dto.post.PostDto;
import com.example.imageboard.model.Post;

public class PostMapperImpl implements PostMapper {

    @Override
    public Post createPostDtoToPost(CreatePostDto dto) {
        if (dto == null) {
            return null;
        }

        return new Post(
                dto.getPostName(),
                dto.getAuthorId(),
                dto.getContent()
        );
    }

    @Override
    public PostDto postToPostDto(Post post) {
        if (post == null) {
            return null;
        }

        return new PostDto(
                post.getId(),
                post.getPostName(),
                post.getContent(),
                post.getAuthorId(),
                post.getCreatedAt()
        );
    }
}
