package br.com.vetorsistemas.ronalds_ws.cadastro.veiculo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository para operações de banco de dados relacionadas a imagens de veículos
 */
@Repository
public interface VeiculoImagemRepository extends JpaRepository<VeiculoImagem, Integer> {

    /**
     * Busca todas as imagens de um veículo específico
     * @param codVei Código do veículo
     * @return Lista de imagens do veículo
     */
    List<VeiculoImagem> findByCodVei(Integer codVei);

    /**
     * Deleta todas as imagens de um veículo específico
     * @param codVei Código do veículo
     * @return Quantidade de imagens deletadas
     */
    @Modifying
    @Query("DELETE FROM VeiculoImagem v WHERE v.codVei = :codVei")
    long deleteByCodVei(@Param("codVei") Integer codVei);

    /**
     * Conta quantas imagens um veículo possui
     * @param codVei Código do veículo
     * @return Quantidade de imagens
     */
    long countByCodVei(Integer codVei);
}
