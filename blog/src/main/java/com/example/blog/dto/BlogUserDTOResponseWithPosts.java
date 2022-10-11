package com.example.blog.dto;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BlogUserDTOResponseWithPosts {

    private Long blogUserId;
    private String blogUserFirstName;
    private String blogUserLastName;
    private String username;
    private List<BlogPostDTOResponse> posts;
}
