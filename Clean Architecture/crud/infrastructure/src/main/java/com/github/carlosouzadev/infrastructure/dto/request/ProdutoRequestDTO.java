package com.github.carlosouzadev.infrastructure.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

public record ProdutoRequestDTO(String nome, BigDecimal preco, UUID usuarioId) {
}
