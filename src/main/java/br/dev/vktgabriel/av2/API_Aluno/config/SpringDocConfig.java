package br.dev.vktgabriel.av2.API_Aluno.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "AV2 API",
                version = "v1",
                contact = @Contact(
                        name = "Victor Gabriel Pedrosa | 202351052274",
                        email = "victorpedrosa66@yahoo.com.br"
                )
        ),
        security = {
                @SecurityRequirement(name = "baererAuth")
        }
)
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "baererAuth",
        bearerFormat = "JWT",
        scheme = "bearer",
        in = SecuritySchemeIn.HEADER
)
public class SpringDocConfig {
}