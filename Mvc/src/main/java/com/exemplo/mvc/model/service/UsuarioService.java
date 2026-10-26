package com.exemplo.mvc.model.service;

import java.util.List;

import com.exemplo.mvc.model.exception.RegraDeNegocioException;
import com.exemplo.mvc.model.Usuario;
import com.exemplo.mvc.model.repository.ProdutoRepository;
import com.exemplo.mvc.model.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

/**
 * MODEL: operações e regras de negócio do Usuário.
 * Em aplicações Spring, o Model costuma agrupar entidade, repositório e service.
 */
@Service
public class UsuarioService {

    private final UsuarioRepository usuarios;
    private final ProdutoRepository produtos;

    //Construtor
    public UsuarioService(UsuarioRepository usuarios, ProdutoRepository produtos) {
        this.usuarios = usuarios;
        this.produtos = produtos;
    }

    public Usuario cadastrar(String nome) {
        Usuario usuario = new Usuario(nome);
        usuario.validar();
        return usuarios.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return usuarios.findById(id).orElseThrow(
                () -> new RegraDeNegocioException("Usuário " + id + " não encontrado."));
    }

    public Usuario atualizar(Long id, String nome) {
        Usuario usuario = buscarPorId(id);
        usuario.setNome(nome);
        usuario.validar();
        return usuarios.save(usuario);
    }

    public void deletar(Long id) {
        Usuario usuario = buscarPorId(id);
        if (produtos.existsByIdUsuario(id)) {
            throw new RegraDeNegocioException(
                    "Não é possível deletar um usuário que possui produtos.");
        }
        usuarios.delete(usuario);
    }

    public List<Usuario> listar() {
        return usuarios.findAll();
    }
}
