package br.com.vetorsistemas.ronalds_ws.cadastro.condicaoPagVenda;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "CADCPV")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CondicaoPagVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gt_cadcpv_codcpv")
    @SequenceGenerator(
            name = "gt_cadcpv_codcpv",
            sequenceName = "gt_cadcpv_codcpv", // nome real da sequência no banco
            allocationSize = 1                     // incrementa de 1 em 1
    )
    @Column(name = "CODCPV")
    private Integer id;
    @Column(name = "DESCPV", length = 50, nullable = false)
    private String descricao;
    @Column(name = "QTDPAR", nullable = false)
    private Integer quantidadeParcelas;
    @Column(name = "INTERV", nullable = false)
    private Integer intervaloParcelas;
    @Column(name = "INDICE", nullable = false)
    private Double indice;
    @Column(name = "TAXJUR", nullable = false)
    private Double taxaJuros;
    @Column(name = "DATCAD")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dataCadastro;
    @Column(name = "DATATU")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dataAlteracao;
    @Column(name = "ENTRAD", length = 1)
    private String temEntrada;


}
