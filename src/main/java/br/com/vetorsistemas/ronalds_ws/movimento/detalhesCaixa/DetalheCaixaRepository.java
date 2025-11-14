package br.com.vetorsistemas.ronalds_ws.movimento.detalhesCaixa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DetalheCaixaRepository extends JpaRepository<DetalheCaixa, Integer>, JpaSpecificationExecutor<DetalheCaixa> {
}
