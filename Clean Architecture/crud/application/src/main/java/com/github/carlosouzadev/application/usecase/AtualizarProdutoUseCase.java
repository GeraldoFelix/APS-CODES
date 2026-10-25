package com.github.carlosouzadev.application.usecase;

import com.github.carlosouzadev.core.domain.Produto;

import java.math.BigDecimal;
import java.util.UUID;

public interface AtualizarProdutoUseCase {
    Produto executar(UUID id, String nome, BigDecimal preco);
}
