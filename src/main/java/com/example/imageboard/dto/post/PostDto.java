package com.example.imageboard.dto.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class PostDto {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("postName")
    private String postName;

    @NotNull
    @JsonProperty("content")
    private String content;

    @NotNull
    @JsonProperty("authorId")
    private Long authorId;

    @NotNull
    @JsonProperty("createdAt")
    private Date createdAt;
}
