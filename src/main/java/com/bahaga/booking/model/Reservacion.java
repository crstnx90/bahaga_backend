package com.bahaga.booking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Reservaciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservacion_id")
    private Long reservacion_id;

    private Date fecha_evento;
    private Time hora;
    @ManyToOne
    private Salon salon;

    @ManyToMany
    private List<Producto> producto;

    @ManyToMany
    private List<Servicio> servicio;

    private Double total;

    public void calcularTotal(){
        // lógica para calcular el costo total basado en productos y servicios seleccionados
    }

    public void addProducto(List<Producto> producto) {
    }

    public void addServicio(List<Servicio> servicio) {
    }

    public void deleteProducto(List<Producto> producto) {
    }

    public void deleteServicio(List<Servicio> servicio) {

    }

    public Date getFecha() {
        return fecha_evento;
    }

    public void setFecha(Date fecha) {

    }
}
