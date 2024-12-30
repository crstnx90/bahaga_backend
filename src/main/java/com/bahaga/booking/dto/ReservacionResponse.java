package com.bahaga.booking.dto;

import com.bahaga.booking.model.TipoEvento;

import java.sql.Time;
import java.time.LocalDate;

public record ReservacionResponse(
        Long reservacionId,
        LocalDate fechaEvento,
        String hora,
        PersonaResponse persona, // Solo devuelve los detalles de la persona relevantes
        SalonResponse salon,  // Solo devuelve los detalles del salón relevantes
        TipoEvento tipoEvento,
        Integer cantidadPersonas,
        String observaciones
) {
}

