package com.bahaga.booking.model;

import com.bahaga.booking.dto.PersonaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Table(name="Usuarios")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TipoDocumento tipoDocumento;
    private String numeroId;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String pais;
    private String ciudad;
    private LocalDate fechaNacimiento;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    private String correo;
    private String telefono;
    private String password;
    private Boolean admin=false;//por defecto es false

    @OneToMany(mappedBy = "persona")//Relacion con reservaciones
    private List<Reservacion> reservaciones;

    //Constructor que acepta el DTO de PersonaDTO
    public Persona(PersonaDTO datosPersona) {
        this.tipoDocumento = datosPersona.tipoDocumento();
        this.numeroId = datosPersona.numeroId();
        this.nombres = datosPersona.nombres();
        this.apellidos = datosPersona.apellidos();
        this.direccion = datosPersona.direccion();
        this.pais = datosPersona.pais();
        this.ciudad = datosPersona.ciudad();
        this.fechaNacimiento = datosPersona.fechaNacimiento();
        this.sexo = datosPersona.sexo();
        this.correo = datosPersona.correo();
        this.telefono = datosPersona.telefono();
        this.password =datosPersona.password();
        this.admin = datosPersona.admin(); // Asigna el valor de admin
    }

}
