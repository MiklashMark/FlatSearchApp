package by.itacademy.flatSearch.userService.core.utils;

import org.jetbrains.annotations.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Component
public class TimeConverter implements Converter<String, LocalDateTime> {
    public Long convert(@NotNull LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    @Override
    public LocalDateTime convert(@NotNull String millis) {
        return Instant.ofEpochMilli(Long.parseLong(millis))
                .atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
