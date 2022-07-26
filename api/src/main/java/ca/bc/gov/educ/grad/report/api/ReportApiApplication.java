package ca.bc.gov.educ.grad.report.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableCaching
public class ReportApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReportApiApplication.class, args);
    }

}
