package br.com.vetorsistemas.ronalds_ws.cadastro.classificacaoFiscal;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "CLAFIS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassificacaoFiscal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gt_clafis_codloc")
    @SequenceGenerator(
            name = "gt_clafis_codloc",
            sequenceName = "gt_clafis_codloc", // nome real da sequência no banco
            allocationSize = 1                     // incrementa de 1 em 1
    )
    @Column(name = "CODLOC")
    private Integer id;
    @Column(name = "CODNCM", length = 20, nullable = false)
    private String codigoNcm;
    @Column(name = "DESNCM", length = 500, nullable = false)
    private String descricaoNcm;
    @Column(name = "ALQ_IBPT")
    private Double aliquotaIbpt;
    @Column(name = "C_CEST")
    private String codigoCest;


}
