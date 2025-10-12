package br.com.vetorsistemas.ronalds_ws.ronalds_ws.service;

import br.com.vetorsistemas.ronalds_ws.ronalds_ws.dto.LoginRequestDto;
import br.com.vetorsistemas.ronalds_ws.ronalds_ws.dto.UsuarioDto;
import br.com.vetorsistemas.ronalds_ws.ronalds_ws.entity.Usuario;
import br.com.vetorsistemas.ronalds_ws.ronalds_ws.mapper.UsuarioMapper;
import br.com.vetorsistemas.ronalds_ws.ronalds_ws.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional
public class UsuarioService  {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;

    public UsuarioService(UsuarioRepository repository, UsuarioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public UsuarioDto login(LoginRequestDto request) {

        Usuario usuario = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas"));

        // 💡 Em produção, compare hash de senha (BCrypt). Aqui: comparação simples para demo.
        if (!usuario.getSenha().equals(request.getSenha())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciais inválidas");
        }

        System.out.println(usuario.toString());

        return mapper.toDto(usuario);
    }



}
