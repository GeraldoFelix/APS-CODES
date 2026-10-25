package com.github.carlosouzadev.infrastructure.config;

import com.github.carlosouzadev.application.gateway.ProdutoGateway;
import com.github.carlosouzadev.application.gateway.UsuarioGateway;
import com.github.carlosouzadev.application.usecase.*;
import com.github.carlosouzadev.application.usecase.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public CadastrarProdutoUseCase cadastrarProdutoUseCase(
            ProdutoGateway produtoGateway, UsuarioGateway usuarioGateway
    ){
        return new CadastrarProdutoUseCaseImpl(produtoGateway, usuarioGateway);
    }

    @Bean
    public CadastrarUsuarioUseCase cadastrarUsuarioUseCase(
            UsuarioGateway usuarioGateway
    ){
        return new CadastrarUsuarioUseCaseImpl(usuarioGateway);
    }

    @Bean
    BuscarProdutoUseCase buscarProdutoUseCase(ProdutoGateway pg) {
        return new BuscarProdutoUseCaseImpl(pg);
    }

    @Bean
    public BuscarUsuarioUseCase buscarUsuarioUseCase(UsuarioGateway u) {
        return new BuscarUsuarioUseCaseImpl(u);
    }

    @Bean
    AtualizarProdutoUseCase atualizarProdutoUseCase(ProdutoGateway pg, UsuarioGateway ug) {
        return new AtualizarProdutoUseCaseImpl(pg);
    }

    @Bean
    DeletarProdutoUseCase deletarProdutoUseCase(ProdutoGateway pg) {
        return new DeletarProdutoUseCaseImpl(pg);
    }
}
