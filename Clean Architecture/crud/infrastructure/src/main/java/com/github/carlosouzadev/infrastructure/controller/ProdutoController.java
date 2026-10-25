package com.github.carlosouzadev.infrastructure.controller;

import com.github.carlosouzadev.application.usecase.AtualizarProdutoUseCase;
import com.github.carlosouzadev.application.usecase.BuscarProdutoUseCase;
import com.github.carlosouzadev.application.usecase.CadastrarProdutoUseCase;
import com.github.carlosouzadev.application.usecase.DeletarProdutoUseCase;
import com.github.carlosouzadev.core.domain.Produto;
import com.github.carlosouzadev.infrastructure.dto.request.ProdutoRequestDTO;
import com.github.carlosouzadev.infrastructure.dto.response.ProdutoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final CadastrarProdutoUseCase cadastrarProdutoUseCase;
    private final BuscarProdutoUseCase buscarProdutoUseCase;
    private final AtualizarProdutoUseCase atualizarProdutoUseCase;
    private final DeletarProdutoUseCase deletarProdutoUseCase;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody ProdutoRequestDTO dto) {
        Produto produtoSalvo = cadastrarProdutoUseCase
                .executar(dto.nome(), dto.preco(), dto.usuarioId());

        URI uri = gerarHeaderLocation(produtoSalvo.getId());
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable UUID id) {
        Produto produto = buscarProdutoUseCase.buscarPorId(id);
        return ResponseEntity.ok(ProdutoResponseDTO.from(produto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable UUID id,
                                                        @RequestBody ProdutoRequestDTO dto) {
        Produto produto = atualizarProdutoUseCase
                .executar(id, dto.nome(), dto.preco());
        return ResponseEntity.ok(ProdutoResponseDTO.from(produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        deletarProdutoUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }

    private URI gerarHeaderLocation(UUID id) {
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }
}