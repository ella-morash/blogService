package com.example.blog.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TagsDTOResponse {

    private Long tagId;
    private String name;
}
