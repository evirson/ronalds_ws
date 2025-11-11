package br.com.vetorsistemas.ronalds_ws.cadastro.classe;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "CADCLA")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gt_cadcla_codcla")
    @SequenceGenerator(
            name = "gt_cadcla_codcla",
            sequenceName = "gt_cadcla_codcla", // nome real da sequência no banco
            allocationSize = 1                     // incrementa de 1 em 1
    )
    @Column(name = "CODCLA")
    private Integer id;
    @Column(name = "NOMCLA", length = 50, nullable = false)
    private String nomeClasse;
    @Column(name = "TIPCLA", length = 1, nullable = false)
    private String tipoClasse;
    @Column(name = "PERDES")
    private Double percentualDescontoPadrao;
    @Column(name = "DATCAD")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dataCadastro;
    @Column(name = "DATAUT")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dataAlteracao;


}
