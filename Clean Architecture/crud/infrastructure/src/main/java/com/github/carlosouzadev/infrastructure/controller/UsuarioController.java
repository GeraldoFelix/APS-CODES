package com.github.carlosouzadev.infrastructure.controller;

import com.github.carlosouzadev.application.usecase.BuscarUsuarioUseCase;
import com.github.carlosouzadev.application.usecase.CadastrarUsuarioUseCase;
import com.github.carlosouzadev.core.domain.Usuario;
import com.github.carlosouzadev.infrastructure.dto.request.UsuarioRequestDTO;
import com.github.carlosouzadev.infrastructure.dto.response.UsuarioResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final CadastrarUsuarioUseCase cadastrarUsuarioUseCase;
    private final BuscarUsuarioUseCase buscarUsuarioUseCase;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody UsuarioRequestDTO dto){
        Usuario usuarioSalvo = cadastrarUsuarioUseCase.executar(dto.nome());

        URI uri = geralHeaderLocation(usuarioSalvo.getId());

        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable UUID id) {
        Usuario usuario = buscarUsuarioUseCase.buscarPorId(id);
        return ResponseEntity.ok(UsuarioResponseDTO.from(usuario));
    }

    private URI geralHeaderLocation(UUID id){
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}
