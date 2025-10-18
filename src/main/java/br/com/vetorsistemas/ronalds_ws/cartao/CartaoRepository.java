package br.com.vetorsistemas.ronalds_ws.cartao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Integer> {

    Optional<Cartao> findByDescricao(String descricao);

    List<Cartao> findByDescricaoContainingIgnoreCaseOrderByDescricaoAsc(String descricao);

}
