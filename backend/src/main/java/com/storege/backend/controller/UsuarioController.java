package com.storege.backend.controller;

import com.storege.backend.dto.UsuarioResponse;
import com.storege.backend.dto.CadastroRequest;
import com.storege.backend.model.Usuario;
import com.storege.backend.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastrar(@RequestBody CadastroRequest req) {
        Usuario u = usuarioService.registrar(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioResponse.de(u));
    }

}
