package com.bahaga.booking.dto;

import com.bahaga.booking.model.TipoEvento;

public record SalonResponse(
        Long salonId,
        String descripcion,
        Integer capacidad
) {}
