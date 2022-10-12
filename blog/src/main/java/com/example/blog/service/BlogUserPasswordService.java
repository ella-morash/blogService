package com.example.blog.service;

import com.example.blog.entity.BlogUser;

public interface BlogUserPasswordService {

    public void generateAndSavePassword(BlogUser blogUser,String password);

    public BlogUser getMatchedAccount(String username,String password);
}
