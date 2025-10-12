package br.com.vetorsistemas.ronalds_ws.ronalds_ws.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDto {
    @Email @NotBlank
    private String email;

    @NotBlank
    private String senha;
}