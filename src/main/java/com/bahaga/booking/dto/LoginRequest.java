package com.bahaga.booking.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @Email(message = "Inserte un correo válido!")
        @NotBlank
        String correo,
        @NotBlank(message = "La contraseña es obligatoria")
        String password
) {
}
