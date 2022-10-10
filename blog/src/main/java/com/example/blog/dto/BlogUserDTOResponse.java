package com.example.blog.dto;


import com.example.blog.entity.accountstatus.AccountStatus;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BlogUserDTOResponse {


    private Long id;

    private String userName;

    private String firstName;

    private String lastName;

    private AccountStatus accountStatus;

    private LocalDate updatedOn;

    private LocalDate createdOn;
}
