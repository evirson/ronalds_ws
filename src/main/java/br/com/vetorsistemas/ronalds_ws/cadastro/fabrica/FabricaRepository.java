package br.com.vetorsistemas.ronalds_ws.cadastro.fabrica;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FabricaRepository extends JpaRepository<Fabrica, Integer>, JpaSpecificationExecutor<Fabrica> {
}
