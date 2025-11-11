package br.com.vetorsistemas.ronalds_ws.movimento.contaCorrente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, Integer>, JpaSpecificationExecutor<ContaCorrente> {
}
