package com.github.carlosouzadev.application.usecase.impl;

import com.github.carlosouzadev.application.gateway.ProdutoGateway;
import com.github.carlosouzadev.application.usecase.DeletarProdutoUseCase;

import java.util.UUID;

public class DeletarProdutoUseCaseImpl implements DeletarProdutoUseCase {
    private final ProdutoGateway gateway;
    public DeletarProdutoUseCaseImpl(ProdutoGateway gateway) { this.gateway = gateway; }

    @Override
    public void executar(UUID id) {
        gateway.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        gateway.deletar(id);
    }
}
