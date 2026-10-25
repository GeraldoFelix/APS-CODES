package com.github.carlosouzadev.application.usecase;

import com.github.carlosouzadev.core.domain.Produto;

import java.math.BigDecimal;
import java.util.UUID;

public interface CadastrarProdutoUseCase {
    Produto executar(String nome, BigDecimal preco, UUID usuarioId);
}
