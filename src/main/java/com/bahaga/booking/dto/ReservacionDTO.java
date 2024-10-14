package com.bahaga.booking.dto;

import com.bahaga.booking.model.Producto;
import com.bahaga.booking.model.Salon;
import com.bahaga.booking.model.Servicio;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public record ReservacionDTO(Long reservacion_id,
                             Date fecha_evento,
                             Time hora,
                             Salon salon,
                             List<Producto> producto,
                             List<Servicio> servicio,
                             Double total
                             ) {
}
