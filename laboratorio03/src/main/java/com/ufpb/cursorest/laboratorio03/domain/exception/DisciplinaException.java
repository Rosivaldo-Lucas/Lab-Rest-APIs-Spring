package com.ufpb.cursorest.laboratorio03.domain.exception;

public class DisciplinaException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DisciplinaException(String mensagem) {
        super(mensagem);
    }
}
