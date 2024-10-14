package com.bahaga.booking.repository;

import com.bahaga.booking.model.Reservacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReservacionRepository extends JpaRepository<Reservacion, Long> {
    Reservacion save(Reservacion reservacion);

    List<Reservacion> findAll();

    Optional<Reservacion> findById(Long id);

     void deleteById(Long id );
}
