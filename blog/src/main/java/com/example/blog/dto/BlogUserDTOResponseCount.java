package com.example.blog.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BlogUserDTOResponseCount {

    private Long blogUserId;
    private String blogUserFirstName;
    private String blogUserLastName;
    private String username;
    private Integer blogsCount;

}
