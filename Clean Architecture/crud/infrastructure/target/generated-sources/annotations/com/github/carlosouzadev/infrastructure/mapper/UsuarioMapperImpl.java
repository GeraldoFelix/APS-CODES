package com.github.carlosouzadev.infrastructure.mapper;

import com.github.carlosouzadev.core.domain.Usuario;
import com.github.carlosouzadev.infrastructure.model.UsuarioEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-10-21T21:48:42-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25.0.1 (Oracle Corporation)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public Usuario toDomain(UsuarioEntity usuario) {
        if ( usuario == null ) {
            return null;
        }

        String nome = null;

        nome = usuario.getNome();

        Usuario usuario1 = new Usuario( nome );

        usuario1.setId( usuario.getId() );

        return usuario1;
    }

    @Override
    public UsuarioEntity toEntity(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioEntity usuarioEntity = new UsuarioEntity();

        usuarioEntity.setId( usuario.getId() );
        usuarioEntity.setNome( usuario.getNome() );

        return usuarioEntity;
    }
}
