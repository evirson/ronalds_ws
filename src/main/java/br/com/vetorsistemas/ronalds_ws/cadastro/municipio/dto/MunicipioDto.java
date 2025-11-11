package br.com.vetorsistemas.ronalds_ws.cadastro.municipio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MunicipioDto {
    private Integer id;
    private Integer codigoEstado;
    private Integer municipio;
    private Integer inicioCep;
    private Integer finalCep;
}
