package br.com.vetorsistemas.ronalds_ws.grupo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Table(name = "CADGRU")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gt_cadgru_codgru")
    @SequenceGenerator(
            name = "gt_cadgru_codgru",
            sequenceName = "gt_cadgru_codgru", // nome real da sequência no banco
            allocationSize = 1                     // incrementa de 1 em 1
    )
    @Column(name = "CODGRU")
    private Integer id;
    @Column(name = "NOMGRU", length = 50, nullable = false)
    private String nomeGrupo;

}
