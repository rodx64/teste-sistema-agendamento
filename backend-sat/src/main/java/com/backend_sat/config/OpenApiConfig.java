package com.backend_sat.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI testOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Sistema de Agendamento de Transferências Financeiras")
                        .description("Api para Sistema de agendamento de transferências financeiras")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0")
                                .url("http://springdoc.org"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Spring Wiki Documentation")
                );
    }

}
