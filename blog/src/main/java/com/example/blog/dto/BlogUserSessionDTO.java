package com.example.blog.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BlogUserSessionDTO {

    private String sessionId;
    private Long blogUserId;
}
