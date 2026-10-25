package com.github.carlosouzadev.infrastructure.repository;

import com.github.carlosouzadev.infrastructure.model.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {
}
