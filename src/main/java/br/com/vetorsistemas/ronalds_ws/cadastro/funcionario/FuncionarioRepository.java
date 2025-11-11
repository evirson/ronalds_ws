package br.com.vetorsistemas.ronalds_ws.cadastro.funcionario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    Optional<Funcionario> findByCpf(String cpf);

    Page<Funcionario> findByNomeContainingIgnoreCaseOrderByNomeAsc(String nome, Pageable pageable);
}

