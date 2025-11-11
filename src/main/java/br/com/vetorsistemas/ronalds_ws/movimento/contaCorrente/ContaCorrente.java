package br.com.vetorsistemas.ronalds_ws.movimento.contaCorrente;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "CADCTA")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ContaCorrente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gt_cadcta_codcta")
    @SequenceGenerator(
            name = "gt_cadcta_codcta",
            sequenceName = "gt_cadcta_codcta", // nome real da sequência no banco
            allocationSize = 1                     // incrementa de 1 em 1
    )
    @Column(name = "CODCTA")
    private Integer id;
    @Column(name = "CODBAN")
    private Integer banco;
    @Column(name = "CODAGE")
    private Integer agencia;
    @Column(name = "NUMCTA")
    private Integer conta;
    @Column(name = "DESCTA", length = 40)
    private String descricao;
    @Column(name = "DATABE", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dataAbertura;
    @Column(name = "TIPCON", length = 1) //C - conta corrente P - Poupança I - Investimento
    private String tipoConta;
    @Column(name = "DATCAD", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dataCadastro;
    @Column(name = "DATATU", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dataAlteracao;

}
