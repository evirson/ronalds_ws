package br.com.vetorsistemas.ronalds_ws.security.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {


    Optional<Usuario> findByEmailIgnoreCase(String email);

    
}

