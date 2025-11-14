package br.com.vetorsistemas.ronalds_ws.security;

import br.com.vetorsistemas.ronalds_ws.security.usuario.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço responsável pela geração, validação e parsing de tokens JWT
 */
@Component
public class JwtTokenService {

    private static final Logger log = LoggerFactory.getLogger(JwtTokenService.class);
    private static final String CLAIM_USER_ID = "uid";
    private static final String CLAIM_ROLE = "role";

    private final Key key;
    private final long validityMs;

    public JwtTokenService(
            @Value("${security.jwt.secret}") String secret,
            @Value("${security.jwt.expiration:3600000}") long validityMs) {
        this.key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.validityMs = validityMs;
        log.info("JwtTokenService inicializado com expiracao de {} ms ({} horas)",
                validityMs, validityMs / 3600000);
    }

    /**
     * Gera um token JWT para o usuário
     */
    public String generateToken(Usuario usuario) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + validityMs);

        String email = usuario.getEmail();
        String role = usuario.getPerfil() != null ? usuario.getPerfil() : "USER";

        return Jwts.builder()
                .setSubject(email)
                .claim(CLAIM_USER_ID, usuario.getId())
                .claim(CLAIM_ROLE, role)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Valida se o token é válido
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.warn("Token JWT expirado: {}", e.getMessage());
        } catch (JwtException e) {
            log.warn("Token JWT invalido: {}", e.getMessage());
        } catch (Exception e) {
            log.error("Erro ao validar token JWT", e);
        }
        return false;
    }

    /**
     * Extrai a autenticação do token
     */
    public Authentication getAuthentication(String token) {
        Claims claims = extractClaims(token);

        if (claims == null) {
            return null;
        }

        String email = claims.getSubject();
        String role = claims.get(CLAIM_ROLE, String.class);

        List<GrantedAuthority> authorities = role != null
                ? List.of(new SimpleGrantedAuthority("ROLE_" + role))
                : List.of();

        User principal = new User(email, "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    /**
     * Extrai o email do usuário do token
     */
    public String getEmailFromToken(String token) {
        Claims claims = extractClaims(token);
        return claims != null ? claims.getSubject() : null;
    }

    /**
     * Extrai o ID do usuário do token
     */
    public Integer getUserIdFromToken(String token) {
        Claims claims = extractClaims(token);
        return claims != null ? claims.get(CLAIM_USER_ID, Integer.class) : null;
    }

    /**
     * Extrai o perfil/role do usuário do token
     */
    public String getRoleFromToken(String token) {
        Claims claims = extractClaims(token);
        return claims != null ? claims.get(CLAIM_ROLE, String.class) : null;
    }

    /**
     * Verifica se o token está expirado
     */
    public boolean isTokenExpired(String token) {
        try {
            Claims claims = extractClaims(token);
            if (claims == null) {
                return true;
            }
            return claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * Extrai as claims do token
     */
    private Claims extractClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            log.debug("Erro ao extrair claims do token: {}", e.getMessage());
            return null;
        }
    }
}
