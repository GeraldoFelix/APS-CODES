package com.exemplo.mvc.model;

import java.math.BigDecimal;

import com.exemplo.mvc.model.service.ProdutoService;
import com.exemplo.mvc.model.service.UsuarioService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * Preenche o banco simulado (H2) com dados de exemplo ao iniciar.
 * Passa pelos Services, então as regras de negócio também valem aqui.
 * Para começar com o banco vazio: demo.dados-iniciais=false
 */
@Component
@ConditionalOnProperty(name = "demo.dados-iniciais", havingValue = "true")
public class DadosIniciais implements CommandLineRunner {

    private final UsuarioService usuarios;
    private final ProdutoService produtos;

    public DadosIniciais(UsuarioService usuarios, ProdutoService produtos) {
        this.usuarios = usuarios;
        this.produtos = produtos;
    }

    @Override
    public void run(String... args) {
        Usuario ana = usuarios.cadastrar("Ana");
        Usuario bruno = usuarios.cadastrar("Bruno");

        produtos.cadastrar("Arroz 5kg", new BigDecimal("24.90"), ana.getId());
        produtos.cadastrar("Feijão 1kg", new BigDecimal("8.50"), ana.getId());
        produtos.cadastrar("Leite 1L", new BigDecimal("5.20"), bruno.getId());
    }
}
