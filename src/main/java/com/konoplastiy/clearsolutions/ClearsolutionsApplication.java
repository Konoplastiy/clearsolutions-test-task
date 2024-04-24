package com.konoplastiy.clearsolutions;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Test Task at Clear Solutions REST API Documentation",
                description = "Test Task REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Andrew Konoplastiy",
                        email = "konoplastiy@gmail.com"
                ),
                license = @License(
                        name = "Apache 2.0"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Kanap-Test-Task REST API Documentation",
                url = "https://www.localhost:8081/swagger-ui.html"
        )
)
public class ClearsolutionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClearsolutionsApplication.class, args);
    }

}
