package br.com.vetorsistemas.ronalds_ws.fornecedor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer>,
        JpaSpecificationExecutor<Fornecedor> {

}