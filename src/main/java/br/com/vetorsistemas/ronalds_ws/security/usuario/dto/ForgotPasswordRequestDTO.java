package br.com.vetorsistemas.ronalds_ws.security.usuario.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para requisição de recuperação de senha
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados para recuperação de senha via email")
public class ForgotPasswordRequestDTO {

    @NotBlank(message = "Email e obrigatorio")
    @Email(message = "Email invalido")
    @Schema(description = "Email do usuário que esqueceu a senha", example = "usuario@exemplo.com", required = true)
    private String email;
}
