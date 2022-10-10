package com.example.blog.entity.accountstatus;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class AccountStatusConverter implements AttributeConverter<AccountStatus,Integer> {
    @Override
    public Integer convertToDatabaseColumn(AccountStatus accountStatus) {
        return accountStatus == null ? null: accountStatus.getStatusId();
    }

    @Override
    public AccountStatus convertToEntityAttribute(Integer integer) {
        return integer == null ? null: AccountStatus.findByStatusId(integer);
    }
}
