package com.storege.backend.dto;

import com.storege.backend.model.Usuario;

public record UsuarioResponse(
        Long id,
        String nome,
        String email,
        String telefone
) {
    public static UsuarioResponse de(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTelefone()
        );
    }
}