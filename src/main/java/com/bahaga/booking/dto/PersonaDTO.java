package com.bahaga.booking.dto;

import com.bahaga.booking.model.Sexo;
import com.bahaga.booking.model.TipoDocumento;

import java.time.LocalDate;

public record PersonaDTO(
        TipoDocumento tipoDocumento,
        String numeroId,
        String nombres,
        String apellidos,
        String direccion,
        String pais,
        String ciudad,
        LocalDate fechaNacimiento,
        Sexo sexo,
        String correo,
        String telefono,
        String password,
        Boolean admin // Campo para identificar si es cliente o administrador
){

}
