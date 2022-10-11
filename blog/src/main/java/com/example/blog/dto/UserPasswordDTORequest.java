package com.example.blog.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserPasswordDTORequest {


    private String passwordHash;

    private String salt;

}
