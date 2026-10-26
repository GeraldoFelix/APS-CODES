package com.exemplo.mvc.model.exception;

/** Lançada pelo Model quando uma regra de negócio é violada. */
public class RegraDeNegocioException extends RuntimeException {

    public RegraDeNegocioException(String mensagem) {
        super(mensagem);
    }
}
