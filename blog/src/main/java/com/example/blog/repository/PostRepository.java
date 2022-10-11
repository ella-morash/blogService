package com.example.blog.repository;

import com.example.blog.dto.SearchDTORequest;
import com.example.blog.entity.BlogPost;
import com.example.blog.entity.Tag;
import com.example.blog.entity.blogstatus.BlogStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<BlogPost,Long> {

   @Query(value = "FROM BlogPost where blogStatus=1 order by createdOn desc ")
   List<BlogPost> findAllByBlogStatus_Published();

   List<BlogPost> findAllByBlogUser_UserName(String username);

   Integer countBlogPostByBlogUserId(Long id);

   List<BlogPost> findAllByBlogUserIdOrderByCreatedOnDesc(Long id);


}
