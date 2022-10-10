package com.example.blog.entity;


import com.example.blog.entity.blogstatus.BlogStatus;
import com.example.blog.entity.blogstatus.BlogStatusConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "blog_post")
@Entity
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "body")
    private String body;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private BlogUser blogUser;
    @Column(name = "blog_status")
    @Convert(converter = BlogStatusConverter.class)
    private BlogStatus blogStatus;
    @Column(name = "updated_on")
    @LastModifiedDate
    private LocalDate updatedOn;
    @Column(name = "created_on")
    @CreatedDate
    private LocalDate createdOn;


}
