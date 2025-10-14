package br.com.vetorsistemas.ronalds_ws.usuario;

import br.com.vetorsistemas.ronalds_ws.usuario.dto.LoginRequestDTO;
import br.com.vetorsistemas.ronalds_ws.usuario.dto.UsuarioDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@Valid @RequestBody LoginRequestDTO body) {
        return ResponseEntity.ok(usuarioService.login(body));
    }
}

