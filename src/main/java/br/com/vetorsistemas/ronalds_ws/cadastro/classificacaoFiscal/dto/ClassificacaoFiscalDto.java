package br.com.vetorsistemas.ronalds_ws.cadastro.classificacaoFiscal.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassificacaoFiscalDto {

    private Integer id;
    private String codigoNcm;
    private String descricaoNcm;
    private Double aliquotaIbpt;
    private String codigoCest;

}
