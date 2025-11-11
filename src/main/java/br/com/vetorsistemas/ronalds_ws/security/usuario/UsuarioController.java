package br.com.vetorsistemas.ronalds_ws.security.usuario;

import br.com.vetorsistemas.ronalds_ws.security.usuario.dto.LoginRequestDTO;
import br.com.vetorsistemas.ronalds_ws.security.usuario.dto.AuthResponseDTO;
import br.com.vetorsistemas.ronalds_ws.security.usuario.dto.ChangePasswordRequestDTO;
import br.com.vetorsistemas.ronalds_ws.security.usuario.dto.ForgotPasswordRequestDTO;
import org.springframework.security.core.Authentication;
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
    @CrossOrigin
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO body) {
        return ResponseEntity.ok(usuarioService.login(body));
    }

    @PostMapping("/change-password")
    @CrossOrigin
    public ResponseEntity<Void> changePassword(Authentication authentication,
                                               @Valid @RequestBody ChangePasswordRequestDTO body) {
        String email = (String) authentication.getPrincipal();
        usuarioService.changePassword(email, body);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/forgot-password")
    @CrossOrigin
    public ResponseEntity<Void> forgotPassword(@Valid @RequestBody ForgotPasswordRequestDTO body) {
        usuarioService.sendTemporaryPassword(body.getEmail());
        return ResponseEntity.noContent().build();
    }
}
