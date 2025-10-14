package br.com.vetorsistemas.ronalds_ws.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {


    Optional<Usuario> findByEmailIgnoreCase(String email);

    
}

