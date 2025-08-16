package com.example.mailapi.email.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EmailRequest(
        @NotBlank @Email String to,
        @NotBlank String subject,
        @NotBlank String message,
        Boolean html // opcional: se true, envia como HTML
) {}
