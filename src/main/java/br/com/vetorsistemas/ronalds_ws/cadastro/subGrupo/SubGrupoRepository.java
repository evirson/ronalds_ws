package br.com.vetorsistemas.ronalds_ws.cadastro.subGrupo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SubGrupoRepository extends JpaRepository<SubGrupo, Integer>, JpaSpecificationExecutor<SubGrupo> {
}
