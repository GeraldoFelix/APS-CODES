package com.github.carlosouzadev.core.domain;

import java.util.UUID;

public class Usuario {
    private UUID id;
    private String nome;

    public Usuario(String nome) {
        this.id = UUID.randomUUID();
        this.nome = nome;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}
