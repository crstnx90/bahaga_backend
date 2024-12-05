package com.bahaga.booking.controller;

import com.bahaga.booking.dto.ReservacionDTO;
import com.bahaga.booking.dto.ReservacionResponse;
import com.bahaga.booking.model.Persona;
import com.bahaga.booking.model.Reservacion;
import com.bahaga.booking.service.ReservacionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/reservacion")
public class ReservacionController {

    @Autowired
    private ReservacionService reservacionService;

    // Crear una nueva reservacion
    @PostMapping
    public ResponseEntity<ReservacionResponse> createReservacion(@Valid @RequestBody ReservacionDTO reservacionDTO,
                                                                 @RequestParam String correo,
                                                                 @RequestParam String password) {
        try {
            // Crear la reservación pasando el DTO que ya tiene el personaId
            ReservacionResponse nuevaReservacion = reservacionService.createReservacion(reservacionDTO,correo,password);
            return new ResponseEntity<>(nuevaReservacion, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error al crear la reservación: "+e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
