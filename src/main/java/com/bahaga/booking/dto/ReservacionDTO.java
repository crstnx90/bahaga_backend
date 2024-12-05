package com.bahaga.booking.dto;

import com.bahaga.booking.model.Producto;
import com.bahaga.booking.model.Salon;
import com.bahaga.booking.model.Servicio;
import com.bahaga.booking.model.TipoEvento;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public record ReservacionDTO(
        LocalDate fechaEvento,
        Time hora,
        Long salonId,  // SalonId como Long
        Long personaId,
        Double total,
        TipoEvento tipoEvento,
        Integer cantidadPersonas,
        String observaciones
) {}

