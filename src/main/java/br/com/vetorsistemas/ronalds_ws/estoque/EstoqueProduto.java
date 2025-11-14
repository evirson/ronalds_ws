package br.com.vetorsistemas.ronalds_ws.estoque;

import br.com.vetorsistemas.ronalds_ws.cadastro.produto.Produto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "ESTPRO")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EstoqueProduto {

    @Id
    @Column(name = "CODPRO", nullable = false)
    private Integer id;

    @Column(name = "QTDEST")
    private Double quantidadeEstoque;

    @Column(name = "RESEST")
    private Double estoqueReservado;

    @Column(name = "CSGEST")
    private Double estoqueConsignado;

    @Column(name = "DATCAD", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataCadastro;

    @Column(name = "DATATU", nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataAtualizacao;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CODPRO", referencedColumnName = "CODPRO", insertable = false, updatable = false)
    private Produto produto;
}

