package com.bahaga.booking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.flywaydb.core.internal.util.JsonUtils;

@Data
@NoArgsConstructor
@Entity(name="Salon")
public class Salon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre_salon;
    private String descripcion;
    private Integer capacidad;
    private Double precio;


}
