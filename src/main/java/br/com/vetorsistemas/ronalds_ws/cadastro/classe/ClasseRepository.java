package br.com.vetorsistemas.ronalds_ws.cadastro.classe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClasseRepository extends JpaRepository<Classe, Integer>,
        JpaSpecificationExecutor<Classe> {


}