package by.itacademy.parserservice.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Data
@AllArgsConstructor
@ConfigurationProperties("app.parsing.realt")
public class ParsingUrlConfig {
    private final String basic;
    private final String sales;
    private final String rentForLong;
    private final String rentForDay;
}
