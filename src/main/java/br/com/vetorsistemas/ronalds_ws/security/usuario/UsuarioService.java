package br.com.vetorsistemas.ronalds_ws.security.usuario;

import br.com.vetorsistemas.ronalds_ws.security.JwtTokenService;
import br.com.vetorsistemas.ronalds_ws.shared.AppException;
import br.com.vetorsistemas.ronalds_ws.security.usuario.dto.AuthResponseDTO;
import br.com.vetorsistemas.ronalds_ws.security.usuario.dto.LoginRequestDTO;
import br.com.vetorsistemas.ronalds_ws.security.usuario.dto.UsuarioDTO;
import br.com.vetorsistemas.ronalds_ws.security.usuario.dto.ChangePasswordRequestDTO;
import br.com.vetorsistemas.ronalds_ws.shared.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.security.SecureRandom;

/**
 * Serviço de gerenciamento de usuários e autenticação
 */
@Service
public class UsuarioService {

    private static final Logger log = LoggerFactory.getLogger(UsuarioService.class);
    private static final int TEMP_PASSWORD_LENGTH = 12;
    private static final String TEMP_PASSWORD_CHARS = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz23456789@#%?";

    private final UsuarioRepository usuarioRepository;
    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManager;

    public UsuarioService(
            UsuarioRepository usuarioRepository,
            JwtTokenService jwtTokenService,
            PasswordEncoder passwordEncoder,
            EmailService emailService,
            AuthenticationManager authenticationManager) {
        this.usuarioRepository = usuarioRepository;
        this.jwtTokenService = jwtTokenService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Realiza login do usuário e retorna o token JWT
     */
    @Transactional
    public AuthResponseDTO login(LoginRequestDTO request) {
        log.info("Tentativa de login para o email: {}", request.getEmail());

        try {
            // Busca o usuário
            Usuario usuario = usuarioRepository.findByEmailIgnoreCase(request.getEmail())
                    .orElseThrow(() -> new AppException(HttpStatus.UNAUTHORIZED, "Email ou senha invalidos"));

            // Verifica se o usuário está ativo
            if (usuario.getAtivo() != null && !usuario.getAtivo()) {
                log.warn("Tentativa de login de usuario inativo: {}", request.getEmail());
                throw new AppException(HttpStatus.UNAUTHORIZED, "Usuario inativo");
            }

            // Valida a senha (com suporte a senhas legadas)
            validatePassword(usuario, request.getSenha());

            // Atualiza último login
            usuario.setUltimoLogin(LocalDateTime.now());
            usuarioRepository.save(usuario);

            // Gera token JWT
            String token = jwtTokenService.generateToken(usuario);

            log.info("Login bem-sucedido para o usuario: {}", usuario.getEmail());

            // Monta resposta
            return AuthResponseDTO.builder()
                    .token(token)
                    .usuario(mapToDTO(usuario))
                    .build();

        } catch (AppException e) {
            throw e;
        } catch (Exception e) {
            log.error("Erro durante login", e);
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao processar login");
        }
    }

    /**
     * Altera a senha do usuário autenticado
     */
    @Transactional
    public void changePassword(String email, ChangePasswordRequestDTO request) {
        log.info("Alteracao de senha solicitada para o usuario: {}", email);

        Usuario usuario = usuarioRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Usuario nao encontrado"));

        // Valida senha atual
        if (!isPasswordValid(usuario.getSenhaHash(), request.getSenhaAtual())) {
            log.warn("Senha atual incorreta para o usuario: {}", email);
            throw new AppException(HttpStatus.BAD_REQUEST, "Senha atual incorreta");
        }

        // Valida que a nova senha é diferente da atual
        if (request.getSenhaAtual().equals(request.getNovaSenha())) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Nova senha deve ser diferente da senha atual");
        }

        // Atualiza a senha
        usuario.setSenhaHash(passwordEncoder.encode(request.getNovaSenha()));
        usuarioRepository.save(usuario);

        log.info("Senha alterada com sucesso para o usuario: {}", email);
    }

    /**
     * Envia uma senha temporária para o email do usuário
     */
    @Transactional
    public void sendTemporaryPassword(String email) {
        log.info("Senha temporaria solicitada para o email: {}", email);

        Usuario usuario = usuarioRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Usuario nao encontrado"));

        // Gera senha temporária
        String tempPassword = generateTempPassword();

        // Atualiza a senha do usuário
        usuario.setSenhaHash(passwordEncoder.encode(tempPassword));
        usuarioRepository.save(usuario);

        // Envia email com a senha temporária
        String subject = "Recuperacao de Senha - Sistema Ronalds";
        String body = buildPasswordRecoveryEmail(usuario.getNome(), tempPassword);

        try {
            emailService.send(usuario.getEmail(), subject, body);
            log.info("Senha temporaria enviada com sucesso para: {}", email);
        } catch (Exception e) {
            log.error("Erro ao enviar email de recuperacao de senha", e);
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Erro ao enviar email. Contate o administrador.");
        }
    }

    /**
     * Valida a senha do usuário (com suporte a senhas legadas em texto puro)
     */
    private void validatePassword(Usuario usuario, String senhaFornecida) {
        String senhaHash = usuario.getSenhaHash();

        if (senhaHash == null || senhaHash.isBlank()) {
            throw new AppException(HttpStatus.UNAUTHORIZED, "Email ou senha invalidos");
        }

        // Verifica se é hash BCrypt
        if (senhaHash.startsWith("$2a$") || senhaHash.startsWith("$2b$") || senhaHash.startsWith("$2y$")) {
            if (!passwordEncoder.matches(senhaFornecida, senhaHash)) {
                throw new AppException(HttpStatus.UNAUTHORIZED, "Email ou senha invalidos");
            }
        } else {
            // Suporte a senhas legadas em texto puro
            if (!senhaHash.equals(senhaFornecida)) {
                throw new AppException(HttpStatus.UNAUTHORIZED, "Email ou senha invalidos");
            }
            // Migra para BCrypt automaticamente
            log.info("Migrando senha legada para BCrypt: {}", usuario.getEmail());
            usuario.setSenhaHash(passwordEncoder.encode(senhaFornecida));
        }
    }

    /**
     * Verifica se a senha fornecida é válida
     */
    private boolean isPasswordValid(String senhaHash, String senhaFornecida) {
        if (senhaHash == null || senhaHash.isBlank()) {
            return false;
        }

        // BCrypt hash
        if (senhaHash.startsWith("$2a$") || senhaHash.startsWith("$2b$") || senhaHash.startsWith("$2y$")) {
            return passwordEncoder.matches(senhaFornecida, senhaHash);
        }

        // Senha legada em texto puro
        return senhaHash.equals(senhaFornecida);
    }

    /**
     * Gera uma senha temporária segura
     */
    private String generateTempPassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(TEMP_PASSWORD_LENGTH);

        for (int i = 0; i < TEMP_PASSWORD_LENGTH; i++) {
            sb.append(TEMP_PASSWORD_CHARS.charAt(random.nextInt(TEMP_PASSWORD_CHARS.length())));
        }

        return sb.toString();
    }

    /**
     * Constrói o email de recuperação de senha
     */
    private String buildPasswordRecoveryEmail(String nomeUsuario, String tempPassword) {
        return String.format("""
                Ola, %s!

                Conforme solicitado, geramos uma senha temporaria para seu acesso ao sistema.

                Senha temporaria: %s

                Por favor, faca login e altere sua senha em seguida pelo endpoint '/api/usuario/change-password'.

                IMPORTANTE: Esta senha e temporaria e deve ser alterada no proximo acesso.

                Se voce nao solicitou esta recuperacao de senha, ignore este email e entre em contato com o administrador do sistema.

                Atenciosamente,
                Sistema Ronalds
                """, nomeUsuario, tempPassword);
    }

    /**
     * Mapeia a entidade Usuario para DTO
     */
    private UsuarioDTO mapToDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .perfil(usuario.getPerfil())
                .ativo(usuario.getAtivo())
                .dataCadastro(usuario.getDataCadastro())
                .ultimoLogin(usuario.getUltimoLogin())
                .telefone(usuario.getTelefone())
                .celular(usuario.getCelular())
                .build();
    }
}
