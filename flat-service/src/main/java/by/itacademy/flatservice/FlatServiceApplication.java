package by.itacademy.flatservice;

import by.itacademy.flatservice.config.properites.JWTProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableConfigurationProperties({JWTProperty.class})
@EnableTransactionManagement
public class FlatServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlatServiceApplication.class, args);

	}

}
