package com.example.blog.entity.blogstatus;


import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class BlogStatusConverter implements AttributeConverter<BlogStatus,Integer> {
    @Override
    public Integer convertToDatabaseColumn(BlogStatus blogStatus) {
        return blogStatus == null ? null: blogStatus.getStatusId();
    }

    @Override
    public BlogStatus convertToEntityAttribute(Integer integer) {
        return integer == null ? null: BlogStatus.findByStatusId(integer);
    }
}
