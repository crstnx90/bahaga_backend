package com.bahaga.booking.repository;

import com.bahaga.booking.model.Reservacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservacionRepository extends JpaRepository<Reservacion, Long> {
    // Metodo para obtener todas las reservaciones de un usuario por su correo
    List<Reservacion> findByPersonaCorreo(String correo);
}
