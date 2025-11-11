package br.com.vetorsistemas.ronalds_ws.movimento.contasReceber;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ContasReceberRepository extends JpaRepository<ContasReceber, Integer>, JpaSpecificationExecutor<ContasReceber> {
}
