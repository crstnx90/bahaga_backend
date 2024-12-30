package com.bahaga.booking.service;

import com.bahaga.booking.dto.*;
import com.bahaga.booking.model.Persona;
import com.bahaga.booking.model.Reservacion;
import com.bahaga.booking.model.Salon;
import com.bahaga.booking.repository.PersonaRepository;
import com.bahaga.booking.repository.ReservacionRepository;
import com.bahaga.booking.repository.SalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservacionService {

    @Autowired
    private ReservacionRepository reservacionRepository; // Repo de reservacion inyectado

    @Autowired
    private SalonRepository salonRepository;  // Repositorio de Salones inyectado

    @Autowired
    private PersonaRepository personaRepository; //Repo de persona inyectado

    @Autowired
    private PersonaService personaService;//Personaservice inyectado para implementar si la persona inicio sesion

    // Crear y guardar una reservación
    public ReservacionResponse createReservacion(ReservacionDTO reservacionDTO, String correo) {

        // Verificar si la persona con ese correo existe
        Optional<Persona> personaOptional = personaService.getByCorreo(correo);
        if (personaOptional.isEmpty()) {
            throw new IllegalArgumentException("Usuario no encontrado con el correo: " + correo);
        }
        Persona persona = personaOptional.get();

        // Buscar el salón por su ID
        Salon salon = salonRepository.findById(reservacionDTO.salonId())
                .orElseThrow(() -> new IllegalArgumentException("Salón no encontrado con ID: " + reservacionDTO.salonId()));

        // Crear la reservación y asignar los datos
        Reservacion reservacion = new Reservacion();
        reservacion.setFechaEvento(reservacionDTO.fechaEvento());
        reservacion.setHora(reservacionDTO.hora());
        reservacion.setSalon(salon);  // Asocia el salón encontrado
        reservacion.setPersona(persona);
        reservacion.setTipoEvento(reservacionDTO.tipoEvento());
        reservacion.setCantidadPersonas(reservacionDTO.cantidadPersonas());
        reservacion.setObservaciones(reservacionDTO.observaciones());

        // Guardar la reservación en la base de datos
        try {
            Reservacion savedReservacion = reservacionRepository.save(reservacion);

            // Crear el objeto PersonaResponse
            PersonaResponse personaResponse = new PersonaResponse(
                    persona.getId(),
                    persona.getTipoDocumento(),
                    persona.getNumeroId(),
                    persona.getNombres(),
                    persona.getApellidos(),
                    persona.getPais(),
                    persona.getCiudad(),
                    persona.getCorreo(),
                    persona.getTelefono()
            );

            // Crear el objeto SalonResponse
            SalonResponse salonResponse = new SalonResponse(
                    salon.getDescripcion(),
                    salon.getCapacidad()
            );

            // Crear y devolver el objeto ReservacionResponse
            return new ReservacionResponse(
                    savedReservacion.getReservacionId(),
                    savedReservacion.getFechaEvento(),
                    savedReservacion.getHora(),
                    personaResponse,
                    salonResponse,
                    savedReservacion.getTipoEvento(),
                    savedReservacion.getCantidadPersonas(),
                    savedReservacion.getObservaciones()
            );
        } catch (Exception e) {
            throw new IllegalStateException("Error al guardar la reservación: " + e.getMessage(), e);
        }
    }


    // Obtener todas las reservaciones
    public List<Reservacion> getAllReservaciones() {
        return reservacionRepository.findAll();
    }

    // Obtener todas las reservaciones por correo
    public List<Reservacion> getAllReservacionesByCorreo(String correo) {
        return reservacionRepository.findByPersonaCorreo(correo);
    }
}
