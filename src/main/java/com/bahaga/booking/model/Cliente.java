package com.bahaga.booking.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name="Cliente")
public class Cliente extends Persona {
    // Métodos específicos de cliente
    public void realizarReservacion(Salon salon, Date fecha, Time hora,Servicio servicios,Producto productos) {
        Reservacion reservacion=new Reservacion();
        reservacion.setSalon(salon);
        reservacion.setFecha_evento(fecha);
        reservacion.setHora(hora);
        reservacion.setServicio((List<Servicio>) servicios);
        reservacion.setProducto((List<Producto>) productos);
    }

    public void agregarProducto(Producto producto) {
        // productoService.save(producto) TODO
    }

    public void eliminarProducto(Producto producto) {
        // productoService.delete(producto) TODO
    }

    public void agregarServicio(Servicio servicio){
        // servicioService.save(producto) TODO
    }

    public void eliminarServicio(Servicio servicio) {
        // productoService.delete(producto) TODO
    }
}
