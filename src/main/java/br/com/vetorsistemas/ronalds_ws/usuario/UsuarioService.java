package br.com.vetorsistemas.ronalds_ws.usuario;

import br.com.vetorsistemas.ronalds_ws.shared.AppException;
import br.com.vetorsistemas.ronalds_ws.usuario.dto.LoginRequestDTO;
import br.com.vetorsistemas.ronalds_ws.usuario.dto.UsuarioDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioDTO login(LoginRequestDTO req) {
        Usuario usuario = usuarioRepository.findByEmailIgnoreCase(req.getEmail())
                .orElseThrow(() -> new AppException(HttpStatus.UNAUTHORIZED, "Email ou senha inválidos"));

        if (!usuario.getSenha().equals(req.getSenha())) {
            throw new AppException(HttpStatus.UNAUTHORIZED, "Email ou senha inválidos");
        }

        LocalDate hoje = LocalDate.now();
        if (usuario.getValidade() != null && usuario.getValidade().isBefore(hoje)) {
            throw new AppException(HttpStatus.UNAUTHORIZED, "Usuário com validade expirada");
        }

        return UsuarioDTO.builder()
                .id(usuario.getId())
                .email(usuario.getEmail())
                .validade(usuario.getValidade())
                .build();
    }
}

