package com.bahaga.booking.model;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

import java.sql.Time;
import java.util.Timer;

@Entity
@Data
@DiscriminatorValue("Servicio")
public class Servicio extends Producto{
    private Time tiempo_servicio;


}
