package com.bahaga.booking.dto;

import java.time.LocalDate;

public record PersonaDTO(Long id,
                                    String numero_id,
                                    String nombres,
                                    String apellidos,
                                    String direccion,
                                    String pais,
                                    String ciudad,
                                    LocalDate fecha_nacimiento,
                                    String correo,
                                    String telefono) {

}
