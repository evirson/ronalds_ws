package br.com.vetorsistemas.ronalds_ws.security.usuario;

import br.com.vetorsistemas.ronalds_ws.security.JwtTokenService;
import br.com.vetorsistemas.ronalds_ws.shared.AppException;
import br.com.vetorsistemas.ronalds_ws.security.usuario.dto.AuthResponseDTO;
import br.com.vetorsistemas.ronalds_ws.security.usuario.dto.LoginRequestDTO;
import br.com.vetorsistemas.ronalds_ws.security.usuario.dto.UsuarioDTO;
import br.com.vetorsistemas.ronalds_ws.security.usuario.dto.ChangePasswordRequestDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.security.SecureRandom;
import br.com.vetorsistemas.ronalds_ws.shared.EmailService;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    public UsuarioService(UsuarioRepository usuarioRepository, JwtTokenService jwtTokenService, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.usuarioRepository = usuarioRepository;
        this.jwtTokenService = jwtTokenService;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    public AuthResponseDTO login(LoginRequestDTO req) {
        Usuario usuario = usuarioRepository.findByEmailIgnoreCase(req.getEmail())
                .orElseThrow(() -> new AppException(HttpStatus.UNAUTHORIZED, "Email ou senha invalidos"));

        String hash = usuario.getSenhaHash();
        boolean senhaOk;
        boolean usouTextoPuro = false;
        if (hash == null || hash.isBlank()) {
            senhaOk = false;
        } else if (hash.startsWith("$2a$") || hash.startsWith("$2b$") || hash.startsWith("$2y$")) {
            senhaOk = passwordEncoder.matches(req.getSenha(), hash);
        } else {
            // fallback para senhas legadas em texto puro
            senhaOk = hash.equals(req.getSenha());
            usouTextoPuro = senhaOk;
        }

        if (!senhaOk) {
            throw new AppException(HttpStatus.UNAUTHORIZED, "Email ou senha invalidos");
        }

        if (usuario.getAtivo() != null && !usuario.getAtivo()) {
            throw new AppException(HttpStatus.UNAUTHORIZED, "Usuario inativo");
        }

        usuario.setUltimoLogin(LocalDateTime.now());
        if (usouTextoPuro) {
            usuario.setSenhaHash(passwordEncoder.encode(req.getSenha()));
        }
        usuarioRepository.save(usuario);

        String token = jwtTokenService.generateToken(usuario);

        UsuarioDTO dto = UsuarioDTO.builder()
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

        return AuthResponseDTO.builder()
                .token(token)
                .usuario(dto)
                .build();
    }

    public void changePassword(String email, ChangePasswordRequestDTO req) {
        Usuario usuario = usuarioRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Usuario nao encontrado"));

        String hash = usuario.getSenhaHash();
        boolean atualConfere;
        if (hash == null || hash.isBlank()) {
            atualConfere = false;
        } else if (hash.startsWith("$2a$") || hash.startsWith("$2b$") || hash.startsWith("$2y$")) {
            atualConfere = passwordEncoder.matches(req.getSenhaAtual(), hash);
        } else {
            atualConfere = hash.equals(req.getSenhaAtual());
        }

        if (!atualConfere) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Senha atual incorreta");
        }

        usuario.setSenhaHash(passwordEncoder.encode(req.getNovaSenha()));
        usuarioRepository.save(usuario);
    }

    public void sendTemporaryPassword(String email) {
        Usuario usuario = usuarioRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Usuario nao encontrado"));

        String tempPass = generateTempPassword(10);
        usuario.setSenhaHash(passwordEncoder.encode(tempPass));
        usuarioRepository.save(usuario);

        String subject = "Sua senha temporaria";
        String body = "Olá, " + usuario.getNome() + ",\n\n" +
                "Conforme solicitado, geramos uma senha temporária para seu acesso.\n" +
                "Senha temporária: " + tempPass + "\n\n" +
                "Por favor, faça login e altere sua senha em seguida pelo menu 'change-password'.\n\n" +
                "Se você não solicitou, ignore este email.";
        emailService.send(usuario.getEmail(), subject, body);
    }

    private String generateTempPassword(int length) {
        final String chars = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz23456789@#%?";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
