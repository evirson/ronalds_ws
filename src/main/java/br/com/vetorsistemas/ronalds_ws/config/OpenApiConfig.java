package br.com.vetorsistemas.ronalds_ws.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração do OpenAPI/Swagger para documentação da API
 *
 * Como usar o Swagger com JWT:
 * 1. Acesse http://localhost:8080/swagger-ui.html
 * 2. Faça login no endpoint POST /api/usuario/login
 * 3. Copie o token retornado (sem o "Bearer")
 * 4. Clique no botão "Authorize" (cadeado) no topo da página
 * 5. Cole o token no campo "Value"
 * 6. Clique em "Authorize" e depois "Close"
 * 7. Agora todos os endpoints protegidos funcionarão automaticamente
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Ronalds Car API",
                version = "1.0",
                description = """
                        # API REST para o Sistema Ronalds Car

                        ## Como Autenticar

                        1. **Login**: Use o endpoint `POST /api/usuario/login` com seu email e senha
                        2. **Token**: Copie o token JWT retornado na resposta
                        3. **Authorize**: Clique no botão verde 'Authorize' no topo desta página
                        4. **Cole o Token**: Insira apenas o token (sem a palavra "Bearer")
                        5. **Pronto**: Todos os endpoints protegidos agora funcionarão automaticamente!

                        ## Informações
                        - Token válido por: 10 horas
                        - Senha nunca expira
                        - Todos os endpoints (exceto login e forgot-password) requerem autenticação
                        """,
                contact = @Contact(
                        name = "Vetor Sistemas",
                        email = "contato@vetorsistemas.com.br"
                )
        ),
        servers = {
                @Server(url = "/", description = "Servidor padrão")
        }
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        description = """
                ### Autenticação JWT

                Insira o token JWT obtido no endpoint de login.

                **IMPORTANTE**: Cole apenas o token, **SEM** a palavra "Bearer".

                Exemplo: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
                """
)
public class OpenApiConfig {

    /**
     * Configura o OpenAPI para aplicar autenticação JWT globalmente
     */
    @Bean
    public OpenAPI customOpenAPI() {
        // Define o requisito de segurança para todos os endpoints
        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("bearerAuth");

        return new OpenAPI()
                .components(new Components())
                .addSecurityItem(securityRequirement);
    }
}
