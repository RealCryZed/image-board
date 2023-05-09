package com.example.imageboard.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.File;
import java.util.Arrays;
import java.util.Date;

@Data
@Entity
@Table(name = "comment")
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COMMENT_SEQ")
    @SequenceGenerator(name="COMMENT_SEQ", sequenceName="COMMENT_SEQ")
    @Column(name = "id")
    private Long id;

    @Column(name = "author")
    private String author;

    @Column(name = "reply_to")
    private String replyToNickname;

    @Column(name = "content")
    private String content;

    @Transient
    private MultipartFile file;

    @Column(name = "image")
    private byte[] image;

    @Column(name = "base64Image")
    @Transient
    private String base64Image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @Column(name = "post_id", insertable = false, updatable = false)
    private Long post_id;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", replyToNickname='" + replyToNickname + '\'' +
                ", content='" + content + '\'' +
                ", post_id=" + post_id +
                ", createdAt=" + createdAt +
                '}';
    }

    public Comment(String author, String content, Long post_id) {
        this.author = author;
        this.content = content;
        this.post_id = post_id;
    }
}
