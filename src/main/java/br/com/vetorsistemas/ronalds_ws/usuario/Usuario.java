package br.com.vetorsistemas.ronalds_ws.usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "USUARIO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "EMAIL", length = 100, nullable = false)
    private String email;

    @Column(name = "SENHA", length = 100, nullable = false)
    private String senha;

    @Column(name = "VALIDADE", nullable = false)
    private LocalDate validade;
}

