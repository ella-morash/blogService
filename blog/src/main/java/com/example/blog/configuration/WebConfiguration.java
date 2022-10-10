package com.example.blog.configuration;


import com.example.blog.entity.accountstatus.AccountStatus;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new Converter<String, AccountStatus>() {
            @Override
            public AccountStatus convert(String source) {
                return AccountStatus.findByExternalStatusId(source);
            }
        });
    }
}
