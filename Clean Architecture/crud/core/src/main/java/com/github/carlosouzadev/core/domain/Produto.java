package com.github.carlosouzadev.core.domain;

import com.github.carlosouzadev.core.exception.PrecoInvalidoException;

import java.math.BigDecimal;
import java.util.UUID;

public class Produto {
    private UUID id;
    private String nome;
    private BigDecimal preco;
    private UUID usuarioId;

    public Produto(String nome, BigDecimal preco, UUID usuarioId) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.preco = preco;
        this.usuarioId = usuarioId;
    }

    public void setPreco(BigDecimal preco) {
        if (preco.compareTo(BigDecimal.ZERO) == -1){
            throw new PrecoInvalidoException();
        }
        this.preco = preco;
    }
    public BigDecimal getPreco() { return preco; }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public UUID getUsuarioId() { return usuarioId; }
    public void setUsuarioId(UUID usuarioId) { this.usuarioId = usuarioId; }
}
