package com.github.carlosouzadev.infrastructure.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "produtos")
@Data
public class ProdutoEntity {

    @Id
    private UUID id;
    private String nome;
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;
}
