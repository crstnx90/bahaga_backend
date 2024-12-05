package com.bahaga.booking.dto;

import com.bahaga.booking.model.TipoDocumento;

public record PersonaResponse(
        Long id,
        TipoDocumento tipoDocumento,
        String numeroId,
        String nombres,
        String apellidos,
        String pais,
        String ciudad,
        String correo,
        String telefono
) {}
