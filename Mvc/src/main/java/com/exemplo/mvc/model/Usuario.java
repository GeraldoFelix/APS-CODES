package com.exemplo.mvc.model;

import com.exemplo.mvc.model.exception.RegraDeNegocioException;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/** MODEL: dados e regras do Usuário. */
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    protected Usuario() { } // exigido pelo JPA

    public Usuario(String nome) {
        this.nome = nome;
    }

    /** Regra de negócio: o nome é obrigatório. */
    public void validar() {
        if (nome == null || nome.isBlank()) {
            throw new RegraDeNegocioException("O nome do usuário é obrigatório.");
        }
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}
