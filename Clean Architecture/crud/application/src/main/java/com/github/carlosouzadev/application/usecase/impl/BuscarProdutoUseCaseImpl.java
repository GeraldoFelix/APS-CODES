package com.github.carlosouzadev.application.usecase.impl;

import com.github.carlosouzadev.application.gateway.ProdutoGateway;
import com.github.carlosouzadev.application.usecase.BuscarProdutoUseCase;
import com.github.carlosouzadev.core.domain.Produto;

import java.util.UUID;

public class BuscarProdutoUseCaseImpl implements BuscarProdutoUseCase {
    private final ProdutoGateway gateway;

    public BuscarProdutoUseCaseImpl(ProdutoGateway gateway) { this.gateway = gateway; }

    @Override
    public Produto buscarPorId(UUID id) {
        return gateway.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }
}