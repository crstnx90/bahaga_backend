package com.bahaga.booking.dto;

import com.bahaga.booking.model.TipoEvento;

public record SalonResponse(
        String descripcion,
        Integer capacidad
) {}
