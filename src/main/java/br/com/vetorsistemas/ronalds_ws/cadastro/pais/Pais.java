package br.com.vetorsistemas.ronalds_ws.cadastro.pais;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PAISES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gt_paises_codpai")
    @SequenceGenerator(name = "gt_paises_codpai", sequenceName = "gt_paises_codpai", allocationSize = 1)
    @Column(name = "CODPAI", nullable = false)
    private Integer id;

    @Column(name = "DESPAI", length = 100, nullable = false)
    private String nome;
}
