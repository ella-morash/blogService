package com.example.blog.repository;

import com.example.blog.entity.BlogToTag;
import com.example.blog.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogToTagRepository extends JpaRepository<BlogToTag,Long> {

    List<BlogToTag> findAllByBlogPostId(Long id);



    List<BlogToTag> findAllByTagName(String name);

    void deleteAllByBlogPost_Id(Long id);
}
