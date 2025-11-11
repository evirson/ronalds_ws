package br.com.vetorsistemas.ronalds_ws.cadastro.veiculo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "CADVEI")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gt_cadvei_codvei")
    @SequenceGenerator(
            name = "gt_cadvei_codvei",
            sequenceName = "gt_cadvei_codvei",
            allocationSize = 1
    )
    @Column(name = "CODVEI")
    private Integer id;

    @Column(name = "CODCLI", nullable = false)
    private Integer codigoCliente;

    @Column(name = "TIPCAR", length = 25, nullable = false)
    private String tipoCarro;

    @Column(name = "PLAVEI", length = 8, nullable = false)
    private String placa;

    @Column(name = "ANOVEI")
    private Integer ano;

    @Column(name = "CORVEI", length = 25)
    private String cor;

    @Column(name = "COMBUS", length = 25)
    private String combustivel;

    @Column(name = "CHASSI", length = 50)
    private String chassi;

    @Column(name = "MOTORS", length = 25)
    private String motor;

    @Column(name = "MODELO", length = 50)
    private String modelo;

    @Column(name = "KLMVEI", length = 25)
    private String km;

    @Column(name = "DATCAD", nullable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "DATATU", nullable = false)
    private LocalDateTime dataAtualizacao;

    @Column(name = "ARCOND", length = 30)
    private String arCondicionado;

    @Column(name = "DIRECAO", length = 30)
    private String direcao;

    @Column(name = "TRANSMISSAO", length = 10)
    private String transmissao;

    @Column(name = "TAMANHOARO", length = 30)
    private String tamanhoAro;

    @Column(name = "TEMABS", length = 1)
    private String temAbs;

    @Column(name = "OLEOMT", length = 25)
    private String oleoMotor;
}
