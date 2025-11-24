package br.com.vetorsistemas.ronalds_ws.cadastro.cartao;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "CADCRT")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gt_cadcrt_codcrt")
    @SequenceGenerator(
            name = "gt_cadcrt_codcrt",
            sequenceName = "gt_cadcrt_codcrt", // nome real da sequência no banco
            allocationSize = 1                     // incrementa de 1 em 1
    )
    @Column(name = "CODCRT")
    private Integer id;
    @Column(name = "NOMCRT", length = 50, nullable = false)
    private String descricao;
    @Column(name = "TAXADM", nullable = false)
    private Double taxaAdministrativa;
    @Column(name = "DIAFEC", nullable = false)
    private Integer diaFechamento;
    @Column(name = "DIACRE", nullable = false)
    private Integer diaCredito;
    @Column(name = "DATCAD", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dataCadastro;
    @Column(name = "DATATU", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dataAlteracao;

}
