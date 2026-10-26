package com.exemplo.mvc.model;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import com.exemplo.mvc.model.exception.RegraDeNegocioException;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

/**
 * Testa as regras do Model isoladamente: sem servidor, sem banco, sem tela.
 * Essa é uma das vantagens do MVC citadas no seminário (testabilidade).
 */
class ProdutoTest {

    @Test
    void precoNegativoDeveSerRejeitado() {
        Produto produto = new Produto("Arroz 5kg", new BigDecimal("-5"), 1L);

        RegraDeNegocioException erro =
                assertThrows(RegraDeNegocioException.class, produto::validar);

        assertEquals("O preço não pode ser negativo.", erro.getMessage());
    }

    @Test
    void nomeEmBrancoDeveSerRejeitado() {
        Produto produto = new Produto("  ", new BigDecimal("10"), 1L);

        assertThrows(RegraDeNegocioException.class, produto::validar);
    }

    @Test
    void produtoValidoNaoLancaExcecao() {
        Produto produto = new Produto("Arroz 5kg", new BigDecimal("24.90"), 1L);

        assertDoesNotThrow(produto::validar);
    }
}
