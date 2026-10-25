package com.github.carlosouzadev.infrastructure.mapper;

import com.github.carlosouzadev.core.domain.Usuario;
import com.github.carlosouzadev.infrastructure.model.UsuarioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toDomain(UsuarioEntity usuario);
    UsuarioEntity toEntity(Usuario usuario);
}
