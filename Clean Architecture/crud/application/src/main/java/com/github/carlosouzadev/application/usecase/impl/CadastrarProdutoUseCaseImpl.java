package com.github.carlosouzadev.application.usecase.impl;

import com.github.carlosouzadev.application.gateway.ProdutoGateway;
import com.github.carlosouzadev.application.gateway.UsuarioGateway;
import com.github.carlosouzadev.application.usecase.CadastrarProdutoUseCase;
import com.github.carlosouzadev.core.domain.Produto;

import java.math.BigDecimal;
import java.util.UUID;

public class CadastrarProdutoUseCaseImpl implements CadastrarProdutoUseCase {

    private final ProdutoGateway produtoGateway;
    private final UsuarioGateway usuarioGateway;

    public CadastrarProdutoUseCaseImpl(
            ProdutoGateway produtoGateway, UsuarioGateway usuarioGateway
    ) {
        this.produtoGateway = produtoGateway;
        this.usuarioGateway = usuarioGateway;
    }

    @Override
    public Produto executar(String nome, BigDecimal preco, UUID usuarioId) {

        usuarioGateway.buscarPorId(usuarioId).orElseThrow(
                () -> new IllegalArgumentException("Usuário não encontrado com id = " + usuarioId)
        );

        Produto produto = new Produto(nome, preco, usuarioId);

        return produtoGateway.salvar(produto);
    }
}
