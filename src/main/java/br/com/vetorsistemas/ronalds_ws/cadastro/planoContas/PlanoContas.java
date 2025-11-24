package br.com.vetorsistemas.ronalds_ws.cadastro.planoContas;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "CADPLA")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PlanoContas {

    @Id
    @Column(name = "CODPLA", length = 8, nullable = false)
    private String id;
    @Column(name = "NOMPLA", length = 50, nullable = false)
    private String descricao;
    @Column(name = "DATCAD", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dataCadastro;
    @Column(name = "DATATU", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dataAlteracao;

}
