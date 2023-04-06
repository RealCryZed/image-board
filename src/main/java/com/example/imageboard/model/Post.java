package com.example.imageboard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "post")
@Data
@NoArgsConstructor
public class Post {

    @Id
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private Long id;

    @Column(name = "article")
    private String article;

    @Lob
    @Column(name = "content", columnDefinition = "CLOB")
    private String content;

    @Column(name = "author_name")
    private String authorName;

    @JsonIgnore
    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Date createdAt;

    public Post(String article, String content, String authorName) {
        this.article = article;
        this.content = content;
        this.authorName = authorName;
    }
}
