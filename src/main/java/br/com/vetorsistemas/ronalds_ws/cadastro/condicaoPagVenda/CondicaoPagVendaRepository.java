package br.com.vetorsistemas.ronalds_ws.cadastro.condicaoPagVenda;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CondicaoPagVendaRepository extends JpaRepository<CondicaoPagVenda, Integer>, JpaSpecificationExecutor<CondicaoPagVenda> {
}
