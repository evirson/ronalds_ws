package br.com.vetorsistemas.ronalds_ws.municipio;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "CADMUN")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Municipio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gt_cadmun_codmun")
    @SequenceGenerator(
            name = "gt_cadmun_codmun",
            sequenceName = "gt_cadmun_codmun", // nome real da sequência no banco
            allocationSize = 1                     // incrementa de 1 em 1
    )
    @Column(name = "CODMUN", nullable = false)
    private Integer id;
    @Column(name = "CODEST", nullable = false)
    private Integer codigoEstado;
    @Column(name = "NOMMUN", nullable = false, length = 60)
    private Integer municipio;
    @Column(name = "INICEP", nullable = false, length = 8)
    private Integer inicioCep;
    @Column(name = "FIMCEP", nullable = false, length = 8)
    private Integer finalCep;

}
