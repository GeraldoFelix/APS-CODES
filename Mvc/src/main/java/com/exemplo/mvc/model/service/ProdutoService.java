package com.exemplo.mvc.model.service;

import java.math.BigDecimal;
import java.util.List;

import com.exemplo.mvc.model.Produto;
import com.exemplo.mvc.model.exception.RegraDeNegocioException;
import com.exemplo.mvc.model.repository.ProdutoRepository;
import com.exemplo.mvc.model.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

/**
 * MODEL: operações e regras de negócio do Produto.
 * Em aplicações Spring, o Model costuma agrupar entidade, repositório e service.
 */
@Service
public class ProdutoService {

    private final ProdutoRepository produtos;
    private final UsuarioRepository usuarios;

    public ProdutoService(ProdutoRepository produtos, UsuarioRepository usuarios) {
        this.produtos = produtos;
        this.usuarios = usuarios;
    }

    /** Regras do produto + regra que depende de outra entidade. */
    private void validar(Produto produto) {
        produto.validar();
        if (!usuarios.existsById(produto.getIdUsuario())) {
            throw new RegraDeNegocioException(
                    "O usuário " + produto.getIdUsuario() + " não existe.");
        }
    }

    public Produto cadastrar(String nome, BigDecimal preco, Long idUsuario) {
        Produto produto = new Produto(nome, preco, idUsuario);
        validar(produto);
        return produtos.save(produto);
    }

    public Produto buscarPorId(Long id) {
        return produtos.findById(id).orElseThrow(
                () -> new RegraDeNegocioException("Produto " + id + " não encontrado."));
    }

    public Produto atualizar(Long id, String nome, BigDecimal preco, Long idUsuario) {
        Produto produto = buscarPorId(id);
        produto.setNome(nome);
        produto.setPreco(preco);
        produto.setIdUsuario(idUsuario);
        validar(produto);
        return produtos.save(produto);
    }

    public void deletar(Long id) {
        produtos.delete(buscarPorId(id));
    }

    public List<Produto> listar() {
        return produtos.findAll();
    }
}
