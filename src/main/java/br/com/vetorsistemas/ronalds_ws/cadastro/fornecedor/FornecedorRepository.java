package br.com.vetorsistemas.ronalds_ws.cadastro.fornecedor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer>,
        JpaSpecificationExecutor<Fornecedor> {

}