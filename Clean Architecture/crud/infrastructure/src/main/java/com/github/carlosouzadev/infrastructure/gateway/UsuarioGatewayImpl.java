package com.github.carlosouzadev.infrastructure.gateway;

import com.github.carlosouzadev.application.gateway.UsuarioGateway;
import com.github.carlosouzadev.core.domain.Usuario;
import com.github.carlosouzadev.infrastructure.mapper.UsuarioMapper;
import com.github.carlosouzadev.infrastructure.model.UsuarioEntity;
import com.github.carlosouzadev.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UsuarioGatewayImpl implements UsuarioGateway {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Override
    public Usuario cadastrar(Usuario usuario) {
        UsuarioEntity entity = usuarioMapper.toEntity(usuario);
        System.out.println("entity = " + entity.getId());
        UsuarioEntity usuarioSalvo = usuarioRepository.save(usuarioMapper.toEntity(usuario));

        return usuarioMapper.toDomain(usuarioSalvo);
    }

    @Override
    public Optional<Usuario> buscarPorId(UUID id) {
         return usuarioRepository.findById(id).map(usuarioMapper::toDomain);
    }


}
