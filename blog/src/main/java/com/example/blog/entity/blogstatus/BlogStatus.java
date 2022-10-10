package com.example.blog.entity.blogstatus;

import com.example.blog.entity.accountstatus.AccountStatus;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;


@RequiredArgsConstructor
@Getter
public enum BlogStatus {

    PUBLISHED(1,"published"),
    UNPUBLISHED(2,"unpublished"),
    BLOCKED(3,"blocked");


    private final Integer statusId;
    private final String externalStatusId;

    public static BlogStatus findByStatusId(Integer statusId) {
        if (statusId == null) {
            return null;
        }

        return Arrays.stream(BlogStatus.values())
                .filter(status -> status.getStatusId().equals(statusId))
                .findFirst()
                .orElse(null);
    }

    @JsonCreator
    public static BlogStatus findByExternalStatusId(String externalStatusId) {
        if (externalStatusId == null) {
            return null;
        }

        return Arrays.stream(BlogStatus.values())
                .filter(status -> status.externalStatusId.equals(externalStatusId))
                .findFirst()
                .orElse(null);
    }
}
