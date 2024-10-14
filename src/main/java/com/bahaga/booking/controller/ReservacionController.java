package com.bahaga.booking.controller;

import com.bahaga.booking.model.Reservacion;
import com.bahaga.booking.service.ReservacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/reservation")
public class ReservacionController {

    @Autowired
    private ReservacionService reservacionService;

    // Crear una nueva reservacion
    @PostMapping
    private ResponseEntity<Reservacion> createReservacion(@RequestBody Reservacion reservacion){
        return ResponseEntity.ok(reservacionService.save(reservacion));
    }

    // Obtener una reservación por id
    @GetMapping("/{id}")
    private ResponseEntity<Reservacion> getReservacion(@PathVariable Long id){
        Optional<Reservacion> reservacionOptional=reservacionService.getReservacionById(id);
        return reservacionOptional
                .map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());
    }

    // Actualizar una reservación por id
    @PutMapping("/{id}")
    private ResponseEntity<Reservacion> updateReservacion(@PathVariable Long id, @RequestBody Reservacion reservacion){
        Reservacion updateReservacion=reservacionService.updateReservacion(id, reservacion);
        if(updateReservacion!=null){
            return ResponseEntity.ok(updateReservacion);
        } else{
            return ResponseEntity.notFound().build();//si no se encuentra la reservacion
        }
    }

    //Eliminar reservacion por id
    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteReservacion(@PathVariable Long id){
        reservacionService.deleteReservacion(id);
        return ResponseEntity.noContent().build();
    }

}
