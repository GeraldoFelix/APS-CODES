package com.github.carlosouzadev.application.gateway;

import com.github.carlosouzadev.core.domain.Produto;

import java.util.Optional;
import java.util.UUID;

public interface ProdutoGateway {
    Produto salvar(Produto produto);
    Optional<Produto> buscarPorId(UUID id);
    Produto atualizar(Produto produto);
    void deletar(UUID id);

    //TODO: definir outros métodos de CRUD conforme necessário
}
