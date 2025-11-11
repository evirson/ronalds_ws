package br.com.vetorsistemas.ronalds_ws.movimento.contasPagar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ContasPagarRepository extends JpaRepository<ContasPagar, Integer>,
        JpaSpecificationExecutor<ContasPagar> {

}