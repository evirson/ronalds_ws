package br.com.vetorsistemas.ronalds_ws.security.usuario.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para requisição de alteração de senha
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados para alteração de senha do usuário autenticado")
public class ChangePasswordRequestDTO {

    @NotBlank(message = "Senha atual e obrigatoria")
    @Schema(description = "Senha atual do usuário", example = "senhaAntiga123", required = true)
    private String senhaAtual;

    @NotBlank(message = "Nova senha e obrigatoria")
    @Size(min = 6, message = "Nova senha deve ter no minimo 6 caracteres")
    @Schema(description = "Nova senha desejada (mínimo 6 caracteres)", example = "novaSenha456", required = true)
    private String novaSenha;
}
