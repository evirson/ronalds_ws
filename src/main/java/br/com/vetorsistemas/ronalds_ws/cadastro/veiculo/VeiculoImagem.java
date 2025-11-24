package br.com.vetorsistemas.ronalds_ws.cadastro.veiculo;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidade que representa uma imagem associada a um veículo
 */
@Entity
@Table(name = "VEICULO_IMAGEM", indexes = {
    @Index(name = "idx_veiculo_imagem_codvei", columnList = "CODVEI")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VeiculoImagem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gt_veiculo_imagem_id")
    @SequenceGenerator(name = "gt_veiculo_imagem_id", sequenceName = "gt_veiculo_imagem_id", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CODVEI", nullable = false)
    private Integer codVei;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CODVEI", referencedColumnName = "CODVEI", insertable = false, updatable = false)
    private Veiculo veiculo;

    @Lob
    @Column(name = "DOCUMENTO", nullable = false)
    private byte[] documento;

    @Column(name = "NOMEARQ", length = 255, nullable = false)
    private String nomeArq;

    @Column(name = "CONTENT_TYPE", length = 100)
    private String contentType;

    @Column(name = "TAMANHO")
    private Long tamanho;
}
