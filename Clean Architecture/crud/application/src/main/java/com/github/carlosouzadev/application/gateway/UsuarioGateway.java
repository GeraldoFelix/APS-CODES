package com.github.carlosouzadev.application.gateway;

import com.github.carlosouzadev.core.domain.Usuario;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioGateway {
    Usuario cadastrar(Usuario usuario);
    Optional<Usuario> buscarPorId(UUID id);


    //TODO: definir outros métodos de CRUD conforme necessário
}
