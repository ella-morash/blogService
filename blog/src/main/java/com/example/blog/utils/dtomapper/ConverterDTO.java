package com.example.blog.utils.dtomapper;


import com.example.blog.dto.*;
import com.example.blog.entity.BlogPost;
import com.example.blog.entity.BlogUser;
import com.example.blog.entity.Tag;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConverterDTO {

    public static BlogPostDTOResponse convertFromBlogPostToDTOShort(BlogPost blogPost) {
        return BlogPostDTOResponse.builder()
                .blogPostId(blogPost.getId())
                .blogPostTittle(blogPost.getTitle())
                .build();
    }

    public static BlogPostDTOResponse convertFromBlogPostToDTONormal(BlogPost blogPost, List<TagsDTOResponse> tagsDTOResponses) {
        return BlogPostDTOResponse.builder()
                .blogPostId(blogPost.getId())
                .blogPostTittle(blogPost.getTitle())
                .blogPostBody(blogPost.getBody())
                .tags(tagsDTOResponses)
                .blogStatus(blogPost.getBlogStatus())
                .authorId(blogPost.getBlogUser().getId())
                .build();
    }

    public static BlogPostDTOResponseFullInfo convertFromBlogPostToDTOFull(BlogPost blogPost,
                                                                           List<TagsDTOResponse> tagsDTOResponses,
                                                                           BlogUserDTOResponse user) {
        return BlogPostDTOResponseFullInfo.builder()
                .blogPostId(blogPost.getId())
                .blogPostTittle(blogPost.getTitle())
                .blogPostBody(blogPost.getBody())
                .tags(tagsDTOResponses)
                .blogUserDTOResponse(user)
                .blogStatus(blogPost.getBlogStatus())
                .createdOn(blogPost.getCreatedOn())
                .updatedOn(blogPost.getCreatedOn())
                .build();
    }

    public static Tag TagDtoToEntity(TagDTORequest tagDTORequest){

        return Tag.builder()
                .name(tagDTORequest.getName())
                .build();
    }

    public static TagsDTOResponse entityTagToDTO(Tag tag) {

        return TagsDTOResponse.builder()
                .name(tag.getName())
                .build();
    }

    public static BlogUserDTOResponseCount convertBlogUserToDtoResponse(BlogUser blogUser
            , Integer count) {

        return BlogUserDTOResponseCount.builder()
                .blogUserId(blogUser.getId())
                .blogUserFirstName(blogUser.getFirstName())
                .blogUserLastName(blogUser.getLastName())
                .username(blogUser.getUserName())
                .blogsCount(count)
                .build();
    }

    public static BlogUserDTOResponseWithPosts convertToBlogUserDToWithPosts(BlogUser user, List<BlogPostDTOResponse> posts) {

        return BlogUserDTOResponseWithPosts.builder()
                .blogUserId(user.getId())
                .blogUserFirstName(user.getFirstName())
                .blogUserLastName(user.getLastName())
                .username(user.getUserName())
                .posts(posts)

                .build();
    }
}
