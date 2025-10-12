package br.com.vetorsistemas.ronalds_ws.ronalds_ws.repository;

import br.com.vetorsistemas.ronalds_ws.ronalds_ws.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Consulta por email
    Optional<Usuario> findByEmail(String email);

    // Consulta por email e senha (ex: para autenticação)
    Optional<Usuario> findByEmailAndSenha(String email, String senha);


}
