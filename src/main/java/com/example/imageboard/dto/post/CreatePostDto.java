package com.example.imageboard.dto.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreatePostDto {

    @NotNull
    @JsonProperty("postName")
    private String postName;

    @NotNull
    @JsonProperty("content")
    private String content;

    @NotNull
    @JsonProperty("authorId")
    private Long authorId;
}
