package br.com.vetorsistemas.ronalds_ws.movimento.detalhesCaixa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "DETCAX")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DetalheCaixa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gt_detcax_codloc")
    @SequenceGenerator(
            name = "gt_detcax_codloc",
            sequenceName = "gt_detcax_codloc",
            allocationSize = 1
    )
    @Column(name = "CODDET")
    private Integer id;

    @Column(name = "CODCAX", nullable = false)
    private Integer codigoCaixa;

    @Column(name = "CODPLA", length = 8, nullable = false)
    private String planoContas;

    @Column(name = "NUMDOC", length = 15)
    private String numeroDocumento;

    @Column(name = "HISTOR", length = 50)
    private String historico;

    @Column(name = "DATMOV", nullable = false)
    private LocalDateTime dataMovimento;

    @Column(name = "VALMOV")
    private Double valorMovimento;

    @Column(name = "DEBCRE", length = 1, nullable = false)
    private String debitoCredito;

    @Column(name = "STATUS", length = 1, nullable = false)
    private String status;
}
