package br.com.vetorsistemas.ronalds_ws.cadastro.veiculo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VeiculoDto {
    private Integer id;

    @NotNull
    private Integer codigoCliente;

    @NotBlank
    @Size(max = 25)
    private String tipoCarro;

    @NotBlank
    @Size(max = 8)
    private String placa;

    private Integer ano;

    @Size(max = 25)
    private String cor;

    @Size(max = 25)
    private String combustivel;

    @Size(max = 50)
    private String chassi;

    @Size(max = 25)
    private String motor;

    @Size(max = 50)
    private String modelo;

    @Size(max = 25)
    private String km;

    @Size(max = 30)
    private String arCondicionado;

    @Size(max = 30)
    private String direcao;

    @Size(max = 10)
    private String transmissao;

    @Size(max = 30)
    private String tamanhoAro;

    @Size(max = 1)
    private String temAbs;

    @Size(max = 25)
    private String oleoMotor;


}
