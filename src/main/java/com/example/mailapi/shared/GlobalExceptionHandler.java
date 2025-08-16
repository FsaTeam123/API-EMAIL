package com.example.mailapi.shared;

import com.example.mailapi.email.EmailService.MailSendException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
        var first = ex.getBindingResult().getFieldErrors().stream().findFirst();
        String msg = first.map(f -> f.getField() + " " + f.getDefaultMessage()).orElse("Dados inválidos");
        return ResponseEntity.badRequest().body(err(400, msg));
    }

    @ExceptionHandler(MailSendException.class)
    public ResponseEntity<?> handleMail(MailSendException ex) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(err(502, ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleOther(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err(500, "Erro interno"));
    }

    private Map<String, Object> err(int status, String message) {
        return Map.of(
                "timestamp", Instant.now().toString(),
                "status", status,
                "message", message
        );
    }
}
