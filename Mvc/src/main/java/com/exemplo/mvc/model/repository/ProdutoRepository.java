package com.exemplo.mvc.model.repository;

import com.exemplo.mvc.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

/** MODEL: acesso ao banco. O SQL é gerado aqui, nunca no Controller. */
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    boolean existsByIdUsuario(Long idUsuario);
}
