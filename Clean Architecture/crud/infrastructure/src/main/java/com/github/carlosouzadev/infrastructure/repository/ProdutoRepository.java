package com.github.carlosouzadev.infrastructure.repository;

import com.github.carlosouzadev.infrastructure.model.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, UUID> {
}
