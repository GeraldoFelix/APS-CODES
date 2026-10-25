package com.github.carlosouzadev.application.usecase.impl;

import com.github.carlosouzadev.application.gateway.UsuarioGateway;
import com.github.carlosouzadev.application.usecase.CadastrarUsuarioUseCase;
import com.github.carlosouzadev.core.domain.Usuario;

public class CadastrarUsuarioUseCaseImpl implements CadastrarUsuarioUseCase {
    private final UsuarioGateway usuarioGateway;

    public CadastrarUsuarioUseCaseImpl(UsuarioGateway usuarioGateway) {
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public Usuario executar(String nome) {

        Usuario usuario = new Usuario(nome);

        System.out.println(usuario.getId());

        return usuarioGateway.cadastrar(usuario);
    }
}
