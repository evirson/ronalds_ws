package br.com.vetorsistemas.ronalds_ws.cadastro.pais;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PaisRepository extends JpaRepository<Pais, Integer>, JpaSpecificationExecutor<Pais> {
}
