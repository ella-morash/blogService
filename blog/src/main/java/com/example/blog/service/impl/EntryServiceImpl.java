package com.example.blog.service.impl;

import com.example.blog.dto.BlogUserSessionDTO;
import com.example.blog.dto.LoginDTORequest;
import com.example.blog.dto.LoginDTOResponse;
import com.example.blog.dto.RegistrationDTORequest;
import com.example.blog.entity.BlogUser;
import com.example.blog.entity.BlogUserSession;
import com.example.blog.repository.BlogUserRepository;
import com.example.blog.repository.BlogUserSessionRepository;
import com.example.blog.service.BlogUserPasswordService;
import com.example.blog.service.BlogUserService;
import com.example.blog.service.EntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional
public class EntryServiceImpl implements EntryService {

    private final BlogUserPasswordService passwordService;
    private final BlogUserRepository blogUserRepository;
    private final BlogUserSessionRepository blogUserSessionRepository;
    @Override
    public void register(RegistrationDTORequest registrationDTORequest) {

        BlogUser blogUser = BlogUser.builder()
                .userName(registrationDTORequest.getUsername())
                .firstName(registrationDTORequest.getFirstName())
                .lastName(registrationDTORequest.getLastName())
                .build();

        blogUserRepository.save(blogUser);

        passwordService.generateAndSavePassword(blogUser, registrationDTORequest.getPassword());

    }

    @Override
    public LoginDTOResponse login(LoginDTORequest loginDTORequest) throws ResponseStatusException {

        var account = passwordService.getMatchedAccount(loginDTORequest.getUsername(), loginDTORequest.getPassword());

        if (account == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        BlogUserSession blogUserSession = BlogUserSession.builder()
                .blogUser(account)
                .sessionId(UUID.randomUUID().toString())
                .build();

        blogUserSessionRepository.save(blogUserSession);

        return LoginDTOResponse.builder()
                .sessionId(blogUserSession.getSessionId())
                .build();
    }

    @Override
    public void logout(BlogUserSessionDTO sessionDTO) {

        var session = blogUserSessionRepository.findBySessionId(sessionDTO.getSessionId());
        blogUserSessionRepository.delete(session);

    }
}
