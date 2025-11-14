package br.com.vetorsistemas.ronalds_ws.movimento.ordemServico.itemOrdemServico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemOrdemServicoRepository extends JpaRepository<ItemOrdemServico, Integer>, JpaSpecificationExecutor<ItemOrdemServico> {

    List<ItemOrdemServico> findByCodigoOrdemServico(Integer codigoOrdemServico);

    @Modifying
    @Query("DELETE FROM ItemOrdemServico i WHERE i.codigoOrdemServico = :codigoOrdemServico")
    void deleteByCodigoOrdemServico(@Param("codigoOrdemServico") Integer codigoOrdemServico);
}
