package com.github.carlosouzadev.core.exception;

public class PrecoInvalidoException extends RuntimeException {
    public PrecoInvalidoException() {
        super("O preço do produto não pode ser menor que zero");
    }
}
