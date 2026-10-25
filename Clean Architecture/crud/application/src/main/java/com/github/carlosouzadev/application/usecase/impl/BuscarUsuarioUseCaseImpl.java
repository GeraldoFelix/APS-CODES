package com.github.carlosouzadev.application.usecase.impl;

import com.github.carlosouzadev.application.gateway.UsuarioGateway;
import com.github.carlosouzadev.application.usecase.BuscarUsuarioUseCase;
import com.github.carlosouzadev.core.domain.Usuario;

import java.util.UUID;

public class BuscarUsuarioUseCaseImpl implements BuscarUsuarioUseCase {

    private final UsuarioGateway usuarioGateway;

    public BuscarUsuarioUseCaseImpl(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public Usuario buscarPorId(UUID id) {
        return usuarioGateway.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}
