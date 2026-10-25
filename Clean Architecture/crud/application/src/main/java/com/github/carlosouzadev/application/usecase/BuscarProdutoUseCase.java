package com.github.carlosouzadev.application.usecase;

import com.github.carlosouzadev.core.domain.Produto;

import java.util.UUID;

public interface BuscarProdutoUseCase {
    Produto buscarPorId(UUID id);
}
