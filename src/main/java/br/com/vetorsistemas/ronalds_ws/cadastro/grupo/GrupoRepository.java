package br.com.vetorsistemas.ronalds_ws.cadastro.grupo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GrupoRepository extends JpaRepository<Grupo, Integer>, JpaSpecificationExecutor<Grupo> {
}
