package br.com.vetorsistemas.ronalds_ws.estoque;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EstoqueProdutoRepository extends JpaRepository<EstoqueProduto, Integer>, JpaSpecificationExecutor<EstoqueProduto> {
}

