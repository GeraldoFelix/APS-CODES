package com.github.carlosouzadev.application.usecase;

import com.github.carlosouzadev.core.domain.Usuario;

import java.util.UUID;

public interface BuscarUsuarioUseCase {
    Usuario buscarPorId(UUID id);
}
