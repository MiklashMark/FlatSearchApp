package by.itacademy.audit;

import by.itacademy.audit.config.properites.JWTProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableConfigurationProperties({JWTProperty.class})
@SpringBootApplication
@EnableAsync
@EnableTransactionManagement
public class AuditReportServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(AuditReportServiceApp.class, args);
    }

}
