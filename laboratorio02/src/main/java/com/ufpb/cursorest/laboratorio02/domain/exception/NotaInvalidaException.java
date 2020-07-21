package com.ufpb.cursorest.laboratorio02.domain.exception;

public class NotaInvalidaException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public NotaInvalidaException(String mensagem) {
    super(mensagem);
  }

}
