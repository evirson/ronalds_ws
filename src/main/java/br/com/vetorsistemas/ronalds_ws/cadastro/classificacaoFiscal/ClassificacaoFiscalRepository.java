package br.com.vetorsistemas.ronalds_ws.cadastro.classificacaoFiscal;

import br.com.vetorsistemas.ronalds_ws.cadastro.classe.Classe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ClassificacaoFiscalRepository extends JpaRepository<ClassificacaoFiscal, Integer>,
        JpaSpecificationExecutor<ClassificacaoFiscal> {

    Optional<ClassificacaoFiscal> findByCodigoNcm(String codigoNcm);


}