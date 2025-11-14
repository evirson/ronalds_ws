package br.com.vetorsistemas.ronalds_ws.security.usuario.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO com dados do usuário (sem informações sensíveis)
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Dados do usuário (sem informações sensíveis)")
public class UsuarioDTO {

    @Schema(description = "ID do usuário", example = "1")
    private Integer id;

    @Schema(description = "Nome completo do usuário", example = "João da Silva")
    private String nome;

    @Schema(description = "Email do usuário", example = "joao.silva@exemplo.com")
    private String email;

    @Schema(description = "Perfil/Role do usuário", example = "ADMIN")
    private String perfil;

    @Schema(description = "Indica se o usuário está ativo", example = "true")
    private Boolean ativo;

    @Schema(description = "Data de cadastro do usuário", example = "2024-01-15T10:30:00")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataCadastro;

    @Schema(description = "Data e hora do último login", example = "2024-11-14T08:15:30")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime ultimoLogin;

    @Schema(description = "Telefone do usuário", example = "(11) 3333-4444")
    private String telefone;

    @Schema(description = "Celular do usuário", example = "(11) 98888-7777")
    private String celular;
}
