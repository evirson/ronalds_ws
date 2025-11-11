package br.com.vetorsistemas.ronalds_ws.tools;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("genhash")
public class HashGenerator implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;

    public HashGenerator(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        String raw = "12345678";
        String hash = passwordEncoder.encode(raw);
        System.out.println("BCrypt hash for '" + raw + "': " + hash);
    }
}

