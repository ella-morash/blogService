package com.example.blog.service.impl;

import com.example.blog.entity.BlogUser;
import com.example.blog.entity.BlogUserPassword;
import com.example.blog.repository.BlogUserPasswordRepository;
import com.example.blog.repository.BlogUserRepository;
import com.example.blog.service.BlogUserPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class BlogUserPasswordServiceImpl implements BlogUserPasswordService {

    private final BlogUserRepository blogUserRepository;
    private final BlogUserPasswordRepository passwordRepository;
    @Override
    public void generateAndSavePassword(BlogUser blogUser, String password) {

        String salt = BCrypt.gensalt();
        String encryptedPassword = BCrypt.hashpw(password,salt);

        BlogUserPassword blogUserPassword = BlogUserPassword.builder()
                .blogUser(blogUser)
                .passwordHash(encryptedPassword)
                .salt(salt)
                .build();

        passwordRepository.save(blogUserPassword);

    }

    @Override
    public BlogUser getMatchedAccount(String username, String password) {
        BlogUser blogUser = blogUserRepository.findByUserName(username);

        if (blogUser == null){
            return null;
        }

        var blogUserPassword = passwordRepository.findByBlogUser(blogUser);
        var actualPasswordHash = BCrypt.hashpw(password,blogUserPassword.getSalt());

        return actualPasswordHash.equals(blogUserPassword.getPasswordHash())? blogUser: null;
    }
}
