package com.ufpb.cursorest.laboratorio02.api.exception;

import com.ufpb.cursorest.laboratorio02.domain.exception.DisciplinaException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DisciplinaException.class)
    public ResponseEntity<Object> handleEntidadeNaoEncontrada(DisciplinaException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        return handleExceptionInternal(ex, null, new HttpHeaders(), status, request);
    }
}
