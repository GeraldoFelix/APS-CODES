package com.github.carlosouzadev.infrastructure.gateway;

import com.github.carlosouzadev.application.gateway.ProdutoGateway;
import com.github.carlosouzadev.core.domain.Produto;
import com.github.carlosouzadev.infrastructure.mapper.ProdutoMapper;
import com.github.carlosouzadev.infrastructure.model.ProdutoEntity;
import com.github.carlosouzadev.infrastructure.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProdutoGatewayImpl implements ProdutoGateway {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    @Override
    public Produto salvar(Produto produto) {

        ProdutoEntity produtoSalvo = produtoRepository.save(produtoMapper.toEntity(produto));

        return produtoMapper.toDomain(produtoSalvo);
    }

    @Override
    public Optional<Produto> buscarPorId(UUID id) {
        return produtoRepository.findById(id).map(produtoMapper::toDomain);
    }

    @Override
    public Produto atualizar(Produto produto) {
        ProdutoEntity atualizado = produtoRepository.save(produtoMapper.toEntity(produto));
        return produtoMapper.toDomain(atualizado);
    }


    @Override
    public void deletar(UUID id) {
        produtoRepository.deleteById(id);
    }
}
