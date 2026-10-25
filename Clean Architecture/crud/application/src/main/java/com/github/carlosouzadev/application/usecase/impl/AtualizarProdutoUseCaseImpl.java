package com.github.carlosouzadev.application.usecase.impl;


import com.github.carlosouzadev.application.gateway.ProdutoGateway;
import com.github.carlosouzadev.application.usecase.AtualizarProdutoUseCase;
import com.github.carlosouzadev.core.domain.Produto;

import java.math.BigDecimal;
import java.util.UUID;

public class AtualizarProdutoUseCaseImpl implements AtualizarProdutoUseCase {
    private final ProdutoGateway produtoGateway;

    public AtualizarProdutoUseCaseImpl(ProdutoGateway gateway) { this.produtoGateway = gateway; }

    @Override
    public Produto executar(UUID id, String nome, BigDecimal preco) {
        Produto produto = produtoGateway.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setNome(nome);
        produto.setPreco(preco);

        return produtoGateway.salvar(produto);
    }

}