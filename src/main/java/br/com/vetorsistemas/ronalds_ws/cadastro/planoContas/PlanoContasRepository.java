package br.com.vetorsistemas.ronalds_ws.cadastro.planoContas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PlanoContasRepository extends JpaRepository<PlanoContas, String>, JpaSpecificationExecutor<PlanoContas> {
}
