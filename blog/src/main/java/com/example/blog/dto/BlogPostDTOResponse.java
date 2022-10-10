package com.example.blog.dto;


import com.example.blog.entity.blogstatus.BlogStatus;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BlogPostDTOResponse {

    private Long blogPostId;
    private String blogPostTittle;
    private String blogPostBody;
    private List<TagsDTOResponse> tags;
    private BlogStatus blogStatus;
    private Long authorId;

}
