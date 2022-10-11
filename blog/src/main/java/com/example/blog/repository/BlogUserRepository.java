package com.example.blog.repository;

import com.example.blog.entity.BlogUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogUserRepository extends JpaRepository<BlogUser,Long> {

    BlogUser findByUserName(String username);


}
