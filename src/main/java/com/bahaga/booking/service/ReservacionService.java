package com.bahaga.booking.service;

import com.bahaga.booking.dto.PersonaDTO;
import com.bahaga.booking.model.Reservacion;
import com.bahaga.booking.repository.AdministradorRepository;
import com.bahaga.booking.repository.ClienteRepository;
import com.bahaga.booking.repository.PersonaRepository;
import com.bahaga.booking.repository.ReservacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservacionService {

    @Autowired
    private ReservacionRepository reservacionRepository;

    //Crea y guarda reservacion
    public Reservacion save(Reservacion reservacion){
        return reservacionRepository.save(reservacion);
    }

    //LIstar todas las reservaciones
    public List<Reservacion> getAllReservaciones(){
        return reservacionRepository.findAll();
    }

    //Obtener reservaciones por id
    public Optional<Reservacion> getReservacionById(Long id){
        return reservacionRepository.findById(id);
    }

    //Actualizar reservacion
    public Reservacion updateReservacion(Long id,Reservacion reservacionDetalles){
        Optional<Reservacion> reservacionOptional=reservacionRepository.findById(id);

        if(reservacionOptional.isPresent()){
            Reservacion reservacion=reservacionOptional.get();

            // Actualizar solo si los detalles son válidos o no nulos
            if (reservacionDetalles.getFecha() != null) {
                reservacion.setFecha(reservacionDetalles.getFecha());
            }
            if (reservacionDetalles.getHora() != null) {
                reservacion.setHora(reservacionDetalles.getHora());
            }
            if (reservacionDetalles.getSalon() != null) {
                reservacion.setSalon(reservacionDetalles.getSalon());
            }
            if (reservacionDetalles.getProducto() != null) {
                reservacion.setProducto(reservacionDetalles.getProducto());
            }
            if (reservacionDetalles.getServicio() != null) {
                reservacion.setServicio(reservacionDetalles.getServicio());
            }

            // Actualizar productos y servicios según sea necesario
            if (reservacionDetalles.getProducto() != null) {
                reservacion.getProducto().addAll(reservacionDetalles.getProducto());
            }
            if (reservacionDetalles.getServicio() != null) {
                reservacion.getServicio().addAll(reservacionDetalles.getServicio());
            }

            return reservacionRepository.save(reservacion);
        }
        return null;
    }

    //Eliminar reservacion
    public void deleteReservacion(Long id){
        reservacionRepository.deleteById(id);
    }

}
