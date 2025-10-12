package br.com.vetorsistemas.ronalds_ws.ronalds_ws.controller;

import br.com.vetorsistemas.ronalds_ws.ronalds_ws.dto.LoginRequestDto;
import br.com.vetorsistemas.ronalds_ws.ronalds_ws.dto.UsuarioDto;
import br.com.vetorsistemas.ronalds_ws.ronalds_ws.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDto> login(@RequestBody @Valid LoginRequestDto request) {

        UsuarioDto usuario = service.login(request);

        return ResponseEntity.ok(usuario);
    }

}