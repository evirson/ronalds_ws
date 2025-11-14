package br.com.vetorsistemas.ronalds_ws.cadastro.estado;

import br.com.vetorsistemas.ronalds_ws.cadastro.pais.Pais;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ESTADOS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gt_estados_codest")
    @SequenceGenerator(name = "gt_estados_codest", sequenceName = "gt_estados_codest", allocationSize = 1)
    @Column(name = "CODEST", nullable = false)
    private Integer id;

    @Column(name = "SIGEST", length = 2)
    private String sigla;

    @Column(name = "NOMEST", length = 30)
    private String nome;

    @Column(name = "CODPAI")
    private Integer codigoPais;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CODPAI", insertable = false, updatable = false)
    private Pais pais;
}
