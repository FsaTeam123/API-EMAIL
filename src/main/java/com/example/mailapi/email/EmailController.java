package com.example.mailapi.email;

import com.example.mailapi.email.dto.EmailRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService service;

    public EmailController(EmailService service) {
        this.service = service;
    }

    @PostMapping("/send")
    public ResponseEntity<?> send(@Valid @RequestBody EmailRequest body) {
        service.send(body);
        return ResponseEntity.ok(new ApiResponse(200, "E-mail enviado com sucesso"));
    }

    record ApiResponse(int status, String message) {}
}
