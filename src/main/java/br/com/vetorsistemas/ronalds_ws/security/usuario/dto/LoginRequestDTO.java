package br.com.vetorsistemas.ronalds_ws.security.usuario.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para requisição de login
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados para autenticação do usuário")
public class LoginRequestDTO {

    @NotBlank(message = "Email e obrigatorio")
    @Email(message = "Email invalido")
    @Schema(description = "Email do usuário", example = "usuario@exemplo.com", required = true)
    private String email;

    @NotBlank(message = "Senha e obrigatoria")
    @Size(min = 3, message = "Senha deve ter no minimo 3 caracteres")
    @Schema(description = "Senha do usuário", example = "senha123", required = true)
    private String senha;
}
