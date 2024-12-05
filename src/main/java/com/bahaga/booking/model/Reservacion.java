package com.bahaga.booking.model;

import com.bahaga.booking.dto.ReservacionDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
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
    @Column(name = "reservacionId")
    private Long reservacionId;

    private LocalDate fechaEvento;
    private Time hora;

    @ManyToOne
    @JoinColumn(name = "persona_id")
    private Persona persona;

    @ManyToOne
    @JoinColumn(name="salonId")//Relacion de salon
    private Salon salon;

    @OneToMany(mappedBy = "reservacion")
    private List<Producto> productos=new ArrayList<>();

    private Double total;

    private TipoEvento tipoEvento;
    private Integer cantidadPersonas;
    private String observaciones;

    // Metodo para calcular el total de la reserva
    public void calcularTotal() {
        if (productos != null && !productos.isEmpty()) {
            this.total = productos.stream()
                    .mapToDouble(producto -> producto.getPrecio() * producto.getCantidad())
                    .sum();
        } else {
            this.total = 0.0;  // Si no hay productos, el total es 0
        }
    }


    // Metodo para calcular el total antes de la persistencia
    @PrePersist
    public void calcularTotalPrePersist() {
        calcularTotal();
    }

    // Constructor que acepta el DTO de ReservacionDTO
    public Reservacion(ReservacionDTO datosReservacion, Persona persona){
        this.fechaEvento = datosReservacion.fechaEvento();
        this.hora = datosReservacion.hora();
        this.total = datosReservacion.total();
        this.persona=persona;
        this.tipoEvento = datosReservacion.tipoEvento();
        this.cantidadPersonas = datosReservacion.cantidadPersonas();
        this.observaciones = datosReservacion.observaciones();
    }

}
