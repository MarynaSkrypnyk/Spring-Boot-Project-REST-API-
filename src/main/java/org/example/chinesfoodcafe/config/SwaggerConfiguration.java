package org.example.chinesfoodcafe.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Chines Food Cafe",
                contact = @Contact(
                        name = "Maryna Skrypnyk",
                        email = "Marikaya.1997@gmail.com"
                )
        )
)
@Configuration
public class SwaggerConfiguration {

}

