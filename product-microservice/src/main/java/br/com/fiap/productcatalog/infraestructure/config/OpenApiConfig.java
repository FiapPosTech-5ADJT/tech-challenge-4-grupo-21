package br.com.fiap.productcatalog.infraestructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Restaurant API")
                        .version("v1")
                        .description("Api para gerenciamento de restaurantes")
                        .termsOfService("dont no")
                        .license(new License().name("apache 2.0")
                                .url("")));
    }
}
