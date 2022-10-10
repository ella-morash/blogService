package com.example.blog.entity.accountstatus;


import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum AccountStatus {
    ACTIVE(1,"active"),
    INACTIVE(0,"inactive");

    private final Integer statusId;
    private final String externalStatusId;

    public static AccountStatus findByStatusId(Integer statusId) {
        if (statusId == null) {
            return null;
        }

        return Arrays.stream(AccountStatus.values())
                .filter(status -> status.getStatusId().equals(statusId))
                .findFirst()
                .orElse(null);
    }

    @JsonCreator
    public static AccountStatus findByExternalStatusId(String externalStatusId) {
        if (externalStatusId == null) {
            return null;
        }

        return Arrays.stream(AccountStatus.values())
                .filter(status -> status.externalStatusId.equals(externalStatusId))
                .findFirst()
                .orElse(null);
    }
}
