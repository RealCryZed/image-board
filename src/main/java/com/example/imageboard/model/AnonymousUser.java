package com.example.imageboard.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "anonymous_users")
@Data
public class AnonymousUser {

    @Id
    @Column(name = "user_id")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "anonymous_user", fetch = FetchType.LAZY)
    private Set<Post> posts;
}
