package com.example.blog.dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegistrationDTORequest {



    private String firstName;

    private String lastName;

    private String username;

    private UserPasswordDTORequest password;
}
