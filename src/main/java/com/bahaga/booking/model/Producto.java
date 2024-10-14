package com.bahaga.booking.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="Productos/Servicios")
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo",discriminatorType = DiscriminatorType.STRING)
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Integer cantidad;
    private Double precio;
    private boolean disponibilidad;
}
