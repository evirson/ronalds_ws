package br.com.vetorsistemas.ronalds_ws.security.usuario;

import br.com.vetorsistemas.ronalds_ws.security.usuario.dto.LoginRequestDTO;
import br.com.vetorsistemas.ronalds_ws.security.usuario.dto.AuthResponseDTO;
import br.com.vetorsistemas.ronalds_ws.security.usuario.dto.ChangePasswordRequestDTO;
import br.com.vetorsistemas.ronalds_ws.security.usuario.dto.ForgotPasswordRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * Controller responsável pelos endpoints de autenticação e gerenciamento de usuários
 */
@RestController
@RequestMapping("/api/usuario")
@Tag(name = "Autenticação", description = "Endpoints de autenticação e gerenciamento de usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Endpoint de login
     */
    @PostMapping("/login")
    @Operation(
            summary = "Realizar login",
            description = "Autentica o usuário e retorna um token JWT válido por 10 horas"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas ou usuário inativo"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos")
    })
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO body) {
        AuthResponseDTO response = usuarioService.login(body);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para alteração de senha
     */
    @PostMapping("/change-password")
    @SecurityRequirement(name = "bearerAuth")
    @Operation(
            summary = "Alterar senha",
            description = "Permite que o usuário autenticado altere sua própria senha. Requer token JWT válido."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Senha alterada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Senha atual incorreta ou dados inválidos"),
            @ApiResponse(responseCode = "401", description = "Token ausente ou inválido"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<Void> changePassword(
            Authentication authentication,
            @Valid @RequestBody ChangePasswordRequestDTO body) {

        String email = authentication.getName();
        usuarioService.changePassword(email, body);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint para recuperação de senha
     */
    @PostMapping("/forgot-password")
    @Operation(
            summary = "Recuperar senha",
            description = "Envia uma senha temporária para o email do usuário. Não requer autenticação."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Email enviado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro ao enviar email")
    })
    public ResponseEntity<Void> forgotPassword(@Valid @RequestBody ForgotPasswordRequestDTO body) {
        usuarioService.sendTemporaryPassword(body.getEmail());
        return ResponseEntity.noContent().build();
    }
}
