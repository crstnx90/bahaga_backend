package com.bahaga.booking.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(name="Administrador")
public class Administrador extends Persona{
    public Administrador(){
        this.setAdmin(true);//brinda la caracteristica de administrador
    }

    // Métodos específicos de administrador
    public void eliminarCliente(Cliente cliente) {
        //clienteService.delete(cliente); este metodo ira especificado en service de cliente para modular metodos
    }

    public void agregarCliente(Cliente cliente) {
        //clienteService.save(cliente); este metodo tambien estara en clienteService
    }

    // Otros métodos como eliminar/agregar reservaciones, productos, servicios

    public void agregarReservacion(Reservacion reservacion){
        //reservacionService.save(reservacion); TODO
    }

    public void eliminarReservacion(Reservacion reservacion){
        //reservacionService.delete(reservacion); TODO
    }

    public void agregarProductos(Producto producto){
        //productoService.save(producto); TODO
    }

    public void eliminarProducto(Producto producto){
        //productoService.delete(producto); TODO
    }

    public void agregarServicio(Servicio servicio){
        //servicioService.save(servicio); TODO
    }

    public void eliminarService(Servicio servicio){
        //servicioService.delete(servicio); TODO
    }


}
