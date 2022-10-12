package com.example.blog.service;

import com.example.blog.dto.BlogUserSessionDTO;
import com.example.blog.dto.LoginDTORequest;
import com.example.blog.dto.LoginDTOResponse;
import com.example.blog.dto.RegistrationDTORequest;

public interface EntryService {

     void register(RegistrationDTORequest registrationDTORequest);

     LoginDTOResponse login(LoginDTORequest loginDTORequest);

     void logout(BlogUserSessionDTO sessionDTO);




}
