package br.com.vetorsistemas.ronalds_ws.security.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO {
    private Integer id;
    private String nome;
    private String email;
    private String perfil;
    private Boolean ativo;
    private LocalDateTime dataCadastro;
    private LocalDateTime ultimoLogin;
    private String telefone;
    private String celular;
}
