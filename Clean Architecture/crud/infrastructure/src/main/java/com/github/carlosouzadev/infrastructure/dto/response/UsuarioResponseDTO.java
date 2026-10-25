package com.github.carlosouzadev.infrastructure.dto.response;

import com.github.carlosouzadev.core.domain.Usuario;

import java.util.UUID;

public record UsuarioResponseDTO(UUID id, String nome) {

    public static UsuarioResponseDTO from(Usuario usuario) {
        return new UsuarioResponseDTO(usuario.getId(), usuario.getNome());
    }
}