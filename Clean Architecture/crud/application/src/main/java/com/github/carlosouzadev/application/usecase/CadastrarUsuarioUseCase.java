package com.github.carlosouzadev.application.usecase;

import com.github.carlosouzadev.core.domain.Usuario;

public interface CadastrarUsuarioUseCase {
    Usuario executar(String nome);
}
