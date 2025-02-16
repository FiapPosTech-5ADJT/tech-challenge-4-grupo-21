package br.com.fiap.logistic.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info()
                .title("Logistic Management API")
                .description("Microsserviço destinado ao gerenciamento de logistica.")
                .version("v1")
        );
    }
}
