package com.example.imageboard.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "post")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "article")
    private String article;

    @Lob
    @Column(name = "content", columnDefinition = "CLOB")
    private String content;

    @Column(name = "image")
    private byte[] image;

    @Transient
    private String base64Image;

    @Column(name = "author_name")
    private String authorName;

    @OneToMany(mappedBy = "post",
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comment> comments;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", article='" + article + '\'' +
                ", content='" + content + '\'' +
                ", authorName='" + authorName + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    public Post(String article, String content, String authorName) {
        this.article = article;
        this.content = content;
        this.authorName = authorName;
    }

    public Post(String article, String content, byte[] image, String authorName) {
        this.article = article;
        this.content = content;
        this.image = image;
        this.authorName = authorName;
    }
}
