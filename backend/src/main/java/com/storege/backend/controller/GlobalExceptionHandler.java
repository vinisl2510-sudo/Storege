package com.storege.backend.controller;

import com.storege.backend.dto.ErroResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Toda IllegalArgumentException lançada nos services vira 400 + {"mensagem": "..."}
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroResponse> tratar(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(new ErroResponse(e.getMessage()));
    }
}
