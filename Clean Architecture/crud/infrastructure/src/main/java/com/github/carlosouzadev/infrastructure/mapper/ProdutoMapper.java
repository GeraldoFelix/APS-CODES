package com.github.carlosouzadev.infrastructure.mapper;

import com.github.carlosouzadev.core.domain.Produto;
import com.github.carlosouzadev.infrastructure.model.ProdutoEntity;
import com.github.carlosouzadev.infrastructure.model.UsuarioEntity;
import com.github.carlosouzadev.infrastructure.repository.UsuarioRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class ProdutoMapper {

    @Autowired
    protected UsuarioRepository usuarioRepository; // Injeta o repositório do Spring Data

    @Mapping(target = "usuarioId", source = "usuario.id")
    public abstract Produto toDomain(ProdutoEntity entity);

    @Mapping(target = "usuario", source = "usuarioId", qualifiedByName = "toUsuarioEntity")
    public abstract ProdutoEntity toEntity(Produto produto);

    @Named("toUsuarioEntity")
    protected UsuarioEntity toUsuarioEntity(UUID id) {
        if (id == null) {
            return null;
        }
        // Pesquisa o utilizador na base de dados através do repositório
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + id));
    }
}
