package com.connectCare.connectCareApi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI().info(
            new Info()
                .title("Connect Care API")
                .version("v1")
                .description("Connect Care API desenvolvida com Spring e PostgreSQL.")
                .license(new License().name("MIT").url("https://www.mit.edu/~amini/LICENSE.md"))
            )
            .tags(getTags())
            .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
            .components(new Components()
                    .addSecuritySchemes("Bearer Authentication", new SecurityScheme().type(SecurityScheme.Type.HTTP)
                            .bearerFormat("JWT").scheme("bearer")));
    }

    private List<Tag> getTags(){
        Tag tag1 = new Tag();
        tag1.setName("Autenticação");
        tag1.setDescription("Rotas de login e cadastro");

        Tag tag2 = new Tag();
        tag2.setName("Pacientes");
        tag2.setDescription("Todas as rotas referentes aos pacientes");

        Tag tag3 = new Tag();
        tag3.setName("Dependentes");
        tag3.setDescription("Todas as rotas referentes aos dependentes");

        Tag tag4 = new Tag();
        tag4.setName("Planos de Saúde");
        tag4.setDescription("Todas as rotas referentes aos planos de saúde");

        Tag tag5 = new Tag();
        tag5.setName("Especialidades");
        tag5.setDescription("Todas as rotas referentes às especialidades");

        Tag tag6 = new Tag();
        tag6.setName("Médicos");
        tag6.setDescription("Todas as rotas referentes aos médicos");

        Tag tag7 = new Tag();
        tag7.setName("Disponibilidades");
        tag7.setDescription("Todas as rotas referentes às disponibilidades");

        Tag tag8 = new Tag();
        tag8.setName("Consultas");
        tag8.setDescription("Todas as rotas referentes às consultas");

        Tag tag9 = new Tag();
        tag9.setName("Usuários");
        tag9.setDescription("Rotas referentes à listagem, atualização e deleção de usuários");

        return Arrays.asList(tag1, tag2, tag3, tag4, tag5, tag6, tag7, tag8, tag9);
    }

}
