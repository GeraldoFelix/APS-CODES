package com.github.carlosouzadev.infrastructure.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "usuarios")
@Data
public class UsuarioEntity {

    @Id
    private UUID id;
    private String nome;
}
