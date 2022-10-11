package com.example.blog.repository;

import com.example.blog.entity.BlogUser;
import com.example.blog.entity.BlogUserPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogUserPasswordRepository extends JpaRepository<BlogUserPassword,Long> {

    BlogUserPassword findByBlogUser(BlogUser blogUser);
}
