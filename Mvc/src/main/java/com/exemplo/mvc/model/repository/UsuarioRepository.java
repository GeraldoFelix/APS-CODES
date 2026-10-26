package com.exemplo.mvc.model.repository;

import com.exemplo.mvc.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/** MODEL: acesso ao banco. O SQL é gerado aqui, nunca no Controller. */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
