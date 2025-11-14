package br.com.vetorsistemas.ronalds_ws.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * Configuração de CORS (Cross-Origin Resource Sharing) da aplicação
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // Permite todas as origens de desenvolvimento e produção
        // Em produção, substitua por origens específicas, ex:
        // config.setAllowedOrigins(List.of("https://seu-dominio.com.br"));
        config.setAllowedOriginPatterns(List.of("*"));

        // Métodos HTTP permitidos
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));

        // Headers permitidos
        config.setAllowedHeaders(List.of("*"));

        // Headers expostos ao cliente (importante para JWT)
        config.setExposedHeaders(List.of("Authorization", "Location"));

        // Permite credenciais (necessário para alguns clientes)
        config.setAllowCredentials(true);

        // Cache da configuração CORS (1 hora em segundos)
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
