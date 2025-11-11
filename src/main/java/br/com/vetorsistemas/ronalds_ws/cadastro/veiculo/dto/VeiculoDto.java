package br.com.vetorsistemas.ronalds_ws.cadastro.veiculo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Integer codigoCliente;
    private String tipoCarro;
    private String placa;
    private Integer ano;
    private String cor;
    private String combustivel;
    private String chassi;
    private String motor;
    private String modelo;
    private String km;
    private String arCondicionado;
    private String direcao;
    private String transmissao;
    private String tamanhoAro;
    private String temAbs;
    private String oleoMotor;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dataCadastro;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dataAtualizacao;
}
