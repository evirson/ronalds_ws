package br.com.vetorsistemas.ronalds_ws.subGrupo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CADGRU")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SubGrupo {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gt_cadsub_codsub")
    @SequenceGenerator(
            name = "gt_cadgru_codsub",
            sequenceName = "gt_cadsub_codsub", // nome real da sequência no banco
            allocationSize = 1                     // incrementa de 1 em 1
    )
    @Column(name = "CODSUB")
    private Integer id;
    @Column(name = "NOMSUB", length = 50, nullable = false)
    private String nomeGrupo;

}
