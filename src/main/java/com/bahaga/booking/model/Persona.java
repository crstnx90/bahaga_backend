package com.bahaga.booking.model;

import com.bahaga.booking.dto.PersonaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Entity
@Table(name="Usuarios")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo_usuario",discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
@AllArgsConstructor

public abstract class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero_id;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String pais;
    private String ciudad;
    private LocalDate fecha_nacimiento;
    private String correo;
    private String telefono;
    @ManyToOne
    private Reservacion reservacion;

    private Boolean admin=false;//por defecto es false

    //Constructor que acepta el DTO de DatosRegistroPersona
    public Persona(PersonaDTO datosPersona) {
        this.numero_id = datosPersona.numero_id();
        this.nombres = datosPersona.nombres();
        this.apellidos = datosPersona.apellidos();
        this.direccion = datosPersona.direccion();
        this.pais = datosPersona.pais();
        this.ciudad = datosPersona.ciudad();
        this.fecha_nacimiento = datosPersona.fecha_nacimiento();
        this.correo = datosPersona.correo();
        this.telefono = datosPersona.telefono();
    }

}
