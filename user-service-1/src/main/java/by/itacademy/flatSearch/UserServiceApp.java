package by.itacademy.flatSearch;

import by.itacademy.user.config.properites.JWTProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties({JWTProperty.class})
@EnableScheduling
public class UserServiceApp {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApp.class, args);
	}

}
