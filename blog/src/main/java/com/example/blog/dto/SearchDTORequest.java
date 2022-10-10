package com.example.blog.dto;


import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SearchDTORequest {

    private String tittle;
    private String userFirstName;
    private String userLastName;
    private List<TagsDTOResponse> tags;
}
