package com.github.carlosouzadev.infrastructure.mapper;

import com.github.carlosouzadev.core.domain.Produto;
import com.github.carlosouzadev.infrastructure.model.ProdutoEntity;
import com.github.carlosouzadev.infrastructure.model.UsuarioEntity;
import java.math.BigDecimal;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-21T21:48:42-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.1 (Oracle Corporation)"
)
@Component
public class ProdutoMapperImpl extends ProdutoMapper {

    @Override
    public Produto toDomain(ProdutoEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UUID usuarioId = null;
        BigDecimal preco = null;
        String nome = null;

        usuarioId = entityUsuarioId( entity );
        preco = entity.getPreco();
        nome = entity.getNome();

        Produto produto = new Produto( nome, preco, usuarioId );

        produto.setId( entity.getId() );

        return produto;
    }

    @Override
    public ProdutoEntity toEntity(Produto produto) {
        if ( produto == null ) {
            return null;
        }

        ProdutoEntity produtoEntity = new ProdutoEntity();

        produtoEntity.setUsuario( toUsuarioEntity( produto.getUsuarioId() ) );
        produtoEntity.setId( produto.getId() );
        produtoEntity.setNome( produto.getNome() );
        produtoEntity.setPreco( produto.getPreco() );

        return produtoEntity;
    }

    private UUID entityUsuarioId(ProdutoEntity produtoEntity) {
        UsuarioEntity usuario = produtoEntity.getUsuario();
        if ( usuario == null ) {
            return null;
        }
        return usuario.getId();
    }
}
