package com.exemplo.mvc.model;

import java.math.BigDecimal;

import com.exemplo.mvc.model.exception.RegraDeNegocioException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/** MODEL: dados e regras do Produto. */
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(precision = 10, scale = 2)
    private BigDecimal preco;

    private Long idUsuario; // coluna id_usuario

    protected Produto() { } // exigido pelo JPA

    public Produto(String nome, BigDecimal preco, Long idUsuario) {
        this.nome = nome;
        this.preco = preco;
        this.idUsuario = idUsuario;
    }

    /** Regras de negócio que dependem só do próprio produto. */
    public void validar() {
        if (nome == null || nome.isBlank()) {
            throw new RegraDeNegocioException("O nome do produto é obrigatório.");
        }
        if (preco == null) {
            throw new RegraDeNegocioException("O preço é obrigatório.");
        }
        if (preco.signum() < 0) {
            throw new RegraDeNegocioException("O preço não pode ser negativo.");
        }
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public BigDecimal getPreco() { return preco; }
    public Long getIdUsuario() { return idUsuario; }

    public void setNome(String nome) { this.nome = nome; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
}
