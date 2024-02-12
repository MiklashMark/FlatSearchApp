package by.itacademy.flatSearch.userService.config;

import by.itacademy.exceptions.error.StructuredErrorResponse;
import by.itacademy.flatSearch.userService.core.utils.TimeConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new TimeConverter());
    }
    @Bean
    public StructuredErrorResponse structuredErrorResponse() {
        return new StructuredErrorResponse();
    }
}

