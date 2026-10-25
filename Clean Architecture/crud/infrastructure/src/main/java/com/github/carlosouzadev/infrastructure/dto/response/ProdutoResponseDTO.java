package com.github.carlosouzadev.infrastructure.dto.response;

import com.github.carlosouzadev.core.domain.Produto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProdutoResponseDTO(UUID id, String nome, BigDecimal preco, UUID usuarioId) {

    public static ProdutoResponseDTO from(Produto p) {
        return new ProdutoResponseDTO(p.getId(), p.getNome(), p.getPreco(), p.getUsuarioId());
    }
}