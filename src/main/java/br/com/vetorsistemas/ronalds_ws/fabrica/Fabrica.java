package br.com.vetorsistemas.ronalds_ws.fabrica;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CADFAB")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Fabrica {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gt_cadfab_codfab")
    @SequenceGenerator(
            name = "gt_cadfab_codfab",
            sequenceName = "gt_cadfab_codfab", // nome real da sequência no banco
            allocationSize = 1                     // incrementa de 1 em 1
    )
    @Column(name = "CODFAB", nullable = false)
    private Integer id;
    @Column(name = "NOMFAB", length = 50, nullable = false)
    private String nomeFabricante;
}
