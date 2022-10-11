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
@Table(name = "blog_user_session")
@Entity
public class BlogUserSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "session_id",unique = true,nullable = false,updatable = false)
    private String sessionId;
    @JoinColumn(name = "blog_user_id",nullable = false)
    @OneToOne
    private BlogUser blogUser;
}
