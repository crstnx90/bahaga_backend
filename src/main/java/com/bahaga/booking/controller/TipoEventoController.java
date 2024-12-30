package com.bahaga.booking.controller;

import com.bahaga.booking.model.TipoEvento;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tipo-evento")
public class TipoEventoController {

    // Método para obtener todos los tipos de evento
    @GetMapping
    public List<String> getAllTipoEventos() {
        return Arrays.stream(TipoEvento.values())
                .map(Enum::name)  // Convertimos los valores del enum en cadenas
                .collect(Collectors.toList());
    }
}
