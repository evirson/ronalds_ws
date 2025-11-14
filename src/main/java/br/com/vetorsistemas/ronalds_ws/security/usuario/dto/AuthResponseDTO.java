package br.com.vetorsistemas.ronalds_ws.security.usuario.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de resposta após autenticação bem-sucedida
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Resposta de autenticação com token JWT e dados do usuário")
public class AuthResponseDTO {

    @Schema(description = "Token JWT para autenticação nas requisições",
            example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;

    @Schema(description = "Dados do usuário autenticado")
    private UsuarioDTO usuario;
}
