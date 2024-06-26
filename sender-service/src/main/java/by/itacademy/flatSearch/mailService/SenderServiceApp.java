package by.itacademy.flatSearch.mailService;

import by.itacademy.flatSearch.mailService.config.security.properties.JWTProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableConfigurationProperties({JWTProperty.class})
@EnableTransactionManagement
@EnableScheduling
@SpringBootApplication
public class SenderServiceApp {
	public static void main(String[] args) {
		SpringApplication.run(SenderServiceApp.class, args);
	}
}
