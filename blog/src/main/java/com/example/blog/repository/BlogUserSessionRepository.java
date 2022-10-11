package com.example.blog.repository;

import com.example.blog.entity.BlogUserSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogUserSessionRepository extends JpaRepository<BlogUserSession,Long> {

    BlogUserSession findBySessionId(String id);
}
