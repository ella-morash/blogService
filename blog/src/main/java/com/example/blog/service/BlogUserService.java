package com.example.blog.service;

import com.example.blog.dto.BlogUserDTOResponseCount;
import com.example.blog.dto.BlogUserDTOResponseWithPosts;

import java.util.List;

public interface BlogUserService {

    List<BlogUserDTOResponseCount> getAllUsersOrderByPostCount();

    BlogUserDTOResponseWithPosts getBlogUserByIdWithAllPostsOrderByCreatedDate(Long id);



}
