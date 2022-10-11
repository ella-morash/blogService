package com.example.blog.service.impl;

import com.example.blog.dto.BlogUserDTOResponseCount;
import com.example.blog.dto.BlogUserDTOResponseWithPosts;
import com.example.blog.repository.BlogUserRepository;
import com.example.blog.repository.PostRepository;
import com.example.blog.service.BlogUserService;
import com.example.blog.utils.dtomapper.ConverterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.List;


@RequiredArgsConstructor
@Service
@Transactional
public class BlogUserServiceImpl implements BlogUserService {

    private final BlogUserRepository blogUserRepository;
    private final PostRepository postRepository;

    @Override
    public List<BlogUserDTOResponseCount> getAllUsersOrderByPostCount() {

       /* return blogUserRepository.findAll()
                .stream()
                .sorted(Comparator.comparingInt(blogUser -> postRepository.countBlogPostByBlogUserId(blogUser.getId())))
                .map(blogUser -> ConverterDTO.convertBlogUserToDtoResponse(blogUser,postRepository.countBlogPostByBlogUserId(blogUser.getId())))
                .toList();
                */

        return blogUserRepository.findAll()
                .stream()
                .map(blogUser -> new AbstractMap.SimpleEntry<>(blogUser,postRepository.countBlogPostByBlogUserId(blogUser.getId())))
                .sorted(Comparator.comparingInt(AbstractMap.SimpleEntry::getValue))
                .map(e->ConverterDTO.convertBlogUserToDtoResponse(e.getKey(),e.getValue()))
                .toList();
    }

    @Override
    public BlogUserDTOResponseWithPosts getBlogUserByIdWithAllPostsOrderByCreatedDate(Long id) {

        var user = blogUserRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND
                        ,String.format("Author with id %d does not exists",id)));

        var posts = postRepository.findAllByBlogUserIdOrderByCreatedOnDesc(user.getId())
                .stream()
                .map(ConverterDTO::convertFromBlogPostToDTOShort)
                .toList();

        return ConverterDTO.convertToBlogUserDToWithPosts(user,posts);
    }
}
