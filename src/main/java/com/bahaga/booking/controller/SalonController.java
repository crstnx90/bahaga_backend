package com.bahaga.booking.controller;

import com.bahaga.booking.model.Salon;
import com.bahaga.booking.repository.SalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/salones")
public class SalonController {

    private final SalonRepository salonRepository;

    // Inyección de dependencia a través del constructor
    public SalonController(SalonRepository salonRepository) {
        this.salonRepository = salonRepository;
    }

    // Metodo GET para obtener todos los salones
    @GetMapping
    public List<Salon> getAllSalones() {
        return salonRepository.findAll();  // Devuelve todos los salones desde la base de datos
    }
}
