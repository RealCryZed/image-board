package com.example.imageboard.mapper;

import com.example.imageboard.dto.post.CreatePostDto;
import com.example.imageboard.dto.post.PostDto;
import com.example.imageboard.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = new PostMapperImpl();

    @Mappings({
            @Mapping(target = "postName", source = "postName"),
            @Mapping(target = "content", source = "content"),
            @Mapping(target = "authorId", source = "authorId"),

    })
    Post createPostDtoToPost(CreatePostDto dto);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "postName", source = "postName"),
            @Mapping(target = "content", source = "content"),
            @Mapping(target = "authorId", source = "authorId"),
            @Mapping(target = "createdAt", source = "createdAt"),
    })
    PostDto postToPostDto(Post post);
}
