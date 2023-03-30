package com.example.imageboard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
@Data
@NoArgsConstructor
public class Post {
    @Id
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private Long id;

    @Column(name = "postname")
    private String postName;

    @Lob
    @Column(name = "content", columnDefinition = "CLOB")
    private String content;

    @ManyToOne()
    @JoinColumn(name = "author_id", insertable = false, updatable = false)
    @JsonIgnore
    private AnonymousUser author;

    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at", updatable = false)
    @UpdateTimestamp
    private Date updatedAt;

    public Post(String postName, Long authorId, String content) {
        this.postName = postName;
        this.authorId = authorId;
        this.content = content;
    }
}
