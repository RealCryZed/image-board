package com.example.imageboard.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "users_table")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USER_SEQ")
    @SequenceGenerator(name="USER_SEQ", sequenceName="USER_SEQ")
    @Column(name = "id")
    private Long id;

    @Size(min = 1, message = "Username must be at least 1 letter")
    @Size(max = 25, message = "Username must be lower than 25 letters")
    private String username;

    @Size(min = 1, message = "Password must be at least 1 letter")
    private String password;

    private String role;
}
