package com.example.blog.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginDTORequest {

    private String username;

    private UserPasswordDTORequest password;
}
