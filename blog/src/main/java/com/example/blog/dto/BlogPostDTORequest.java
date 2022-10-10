package com.example.blog.dto;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BlogPostDTORequest {

    private String tittle;
    private String body;
    private List<TagDTORequest> tags;
    private Long authorId;
}
