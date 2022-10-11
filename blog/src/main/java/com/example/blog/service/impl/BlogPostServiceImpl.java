package com.example.blog.service.impl;

import com.example.blog.dto.*;
import com.example.blog.entity.BlogPost;
import com.example.blog.entity.BlogToTag;
import com.example.blog.entity.blogstatus.BlogStatus;
import com.example.blog.repository.BlogToTagRepository;
import com.example.blog.repository.BlogUserRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.repository.TagRepository;
import com.example.blog.service.BlogPostService;
import com.example.blog.utils.dtomapper.ConverterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BlogPostServiceImpl implements BlogPostService {

    private final PostRepository postRepository;
    private final BlogUserRepository blogUserRepository;
    private final BlogToTagRepository blogToTagRepository;
    private final TagRepository tagRepository;

    @Override
    public List<BlogPostDTOResponse> getAllPublishedPostsOrderedByCreationDate() {

        return  postRepository.findAllByBlogStatus_Published().stream()
                .map(ConverterDTO::convertFromBlogPostToDTOShort)
                .toList();
    }

    @Override
    public BlogPostDTOResponse createPost(BlogPostDTORequest blogPostDTORequest) {

        var author = blogUserRepository.findById(blogPostDTORequest.getAuthorId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("No author with id %d",blogPostDTORequest.getAuthorId())));

        var post = BlogPost.builder()
                .title(blogPostDTORequest.getTittle())
                .body(blogPostDTORequest.getBody())
                .blogUser(author)
                .blogStatus(BlogStatus.PUBLISHED)
                .build();

        postRepository.save(post);

        blogPostDTORequest.getTags().stream()
                .map(ConverterDTO::TagDtoToEntity)
                .forEach(tag -> {
                    var blogToTagRelation = BlogToTag.builder()
                            .blogPost(post)
                            .tag(tag)
                            .build();

                    blogToTagRepository.save(blogToTagRelation);
                });


        return BlogPostDTOResponse.builder()
                .blogPostId(post.getId())
                .blogPostTittle(post.getTitle())
                .blogPostBody(post.getBody())
                .tags(blogToTagRepository.findAllByBlogPostId(post.getId()).stream()
                        .map(relation->ConverterDTO.entityTagToDTO(relation.getTag()))
                        .toList())
                .blogStatus(post.getBlogStatus())
                .authorId(post.getBlogUser().getId())
                .build();
    }

    @Override
    public List<BlogPostDTOResponse> searchPostsByRequest(SearchDTORequest searchDTORequest) {



        return searchDTORequest.getTags()
                .stream()
                .flatMap(tagsDTOResponse -> blogToTagRepository.findAllByTagName(tagsDTOResponse.getName()).stream())
                .map(BlogToTag::getBlogPost)
                .filter(blogPost -> blogPost.getTitle().equals(searchDTORequest.getTittle())&&
                        blogPost.getBlogUser().getFirstName().equals(searchDTORequest.getUserFirstName())&&
                        blogPost.getBlogUser().getLastName().equals(searchDTORequest.getUserLastName())&&
                        blogPost.getBlogStatus().equals(BlogStatus.PUBLISHED))
                .map(ConverterDTO::convertFromBlogPostToDTOShort)
                .toList();

    }

    @Override
    public BlogPostDTOResponseFullInfo getPostByIdWithAnyStatus(Long blogPostId) {

        var post = postRepository.findById(blogPostId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND
                        ,String.format("Article with id %d does not exists",blogPostId)));

        var tags = blogToTagRepository.findAllByBlogPostId(blogPostId)
                .stream()
                .map(blogToTag -> ConverterDTO.entityTagToDTO(blogToTag.getTag()))
                .toList();

        var user = BlogUserDTOResponse.builder()
                .id(post.getBlogUser().getId())
                .userName(post.getBlogUser().getUserName())
                .firstName(post.getBlogUser().getFirstName())
                .lastName(post.getBlogUser().getLastName())
                .build();

        return ConverterDTO.convertFromBlogPostToDTOFull(post,tags,user);
    }

    @Override
    public void updateBloPost(Long postId, UpdatingDTORequest updatingDTORequest) {

        var post = postRepository.findById(postId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND
                        ,String.format("Article with id %d does not exists",postId)));

        post.setTitle(updatingDTORequest.getTittle());
        post.setBody(updatingDTORequest.getBody());

        postRepository.save(post);

        updatingDTORequest.getTags().stream()
                .map(ConverterDTO::TagDtoToEntity)

                .forEach(tag -> {
                    tagRepository.save(tag);

                    var relation = BlogToTag.builder()
                                     .blogPost(post)
                                     .tag(tag)
                            .build();

                    blogToTagRepository.save(relation);


                });




    }

    @Override
    public void publish(Long blogPostId) {

        var post = postRepository.findById(blogPostId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND
                        ,String.format("Article with id %d does not exists",blogPostId)));

        post.setBlogStatus(BlogStatus.PUBLISHED);

        postRepository.save(post);

    }

    @Override
    public void unPublish(Long blogPostId) {

        var post = postRepository.findById(blogPostId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND
                        ,String.format("Article with id %d does not exists",blogPostId)));

        post.setBlogStatus(BlogStatus.UNPUBLISHED);

        postRepository.save(post);

    }

    @Override
    public void block(Long blogPostId) {

        var post = postRepository.findById(blogPostId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND
                        ,String.format("Article with id %d does not exists",blogPostId)));

        post.setBlogStatus(BlogStatus.BLOCKED);

        postRepository.save(post);

    }

    @Override
    public List<BlogPostDTOResponse> getAllPostsByUsername(String userName) {

        return postRepository.findAllByBlogUser_UserName(userName)
                .stream()
                .map(ConverterDTO::convertFromBlogPostToDTOShort)
                .toList();
    }

    @Override
    public void deletePostById(Long postId) {

        postRepository.deleteById(postId);
        blogToTagRepository.deleteAllByBlogPost_Id(postId);

    }
}
