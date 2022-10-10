package com.example.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "blog_user_password")
@Entity
public class BlogUserPassword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "password")
    private String password;
    @Column(name = "salt")
    private String salt;
    @OneToOne
    @JoinColumn(name = "user_id")
    private BlogUser blogUser;



}
