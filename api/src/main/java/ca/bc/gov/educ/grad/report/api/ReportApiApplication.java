package ca.bc.gov.educ.grad.report.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import java.util.logging.LogManager;
import org.slf4j.bridge.SLF4JBridgeHandler;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableCaching
public class ReportApiApplication {

    public static void main(String[] args) {
        // Bridge JUL (java.util.logging) to SLF4J
        LogManager.getLogManager().reset();
        SLF4JBridgeHandler.install();

        SpringApplication.run(ReportApiApplication.class, args);
    }
}
