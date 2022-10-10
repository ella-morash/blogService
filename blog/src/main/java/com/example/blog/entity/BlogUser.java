package com.example.blog.entity;


import com.example.blog.entity.accountstatus.AccountStatus;
import com.example.blog.entity.accountstatus.AccountStatusConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "blog_user")
@Entity
public class BlogUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "account_status")
    @Convert(converter = AccountStatusConverter.class)
    private AccountStatus accountStatus;
    @Column(name = "updated_on")
    @LastModifiedDate
    private LocalDate updatedOn;
    @Column(name = "created_on")
    @CreatedDate
    private LocalDate createdOn;


}
