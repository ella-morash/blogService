package com.example.blog.service;


import com.example.blog.dto.*;

import java.util.List;

public interface BlogPostService {

    List<BlogPostDTOResponse> getAllPublishedPostsOrderedByCreationDate();

    BlogPostDTOResponse createPost(BlogPostDTORequest blogPostDTORequest);

    List<BlogPostDTOResponse> searchPostsByRequest(SearchDTORequest searchDTORequest);

    BlogPostDTOResponseFullInfo getPostByIdWithAnyStatus(Long blogPostId);

    void updateBloPost(Long postId,UpdatingDTORequest updatingDTORequest);

    void publish(Long blogPostId);
    void unPublish(Long blogPostId);
    void block(Long blogPostId);

    List<BlogPostDTOResponse> getAllPostsByUsername(String userName);

    void deletePostById(Long postId);

}
