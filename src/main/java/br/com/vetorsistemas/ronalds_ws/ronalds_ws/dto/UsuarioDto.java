package br.com.vetorsistemas.ronalds_ws.ronalds_ws.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Data
public class UsuarioDto {

    private int id;
    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate validade;
}
