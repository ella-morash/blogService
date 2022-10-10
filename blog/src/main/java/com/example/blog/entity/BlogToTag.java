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
@Table(name = "blog_to_tag")
@Entity
public class BlogToTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "blog_post_id")
    private BlogPost blogPost;
    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;
}
