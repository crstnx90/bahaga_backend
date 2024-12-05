package com.bahaga.booking.service;

import com.bahaga.booking.dto.PersonaResponse;
import com.bahaga.booking.dto.ReservacionDTO;
import com.bahaga.booking.dto.ReservacionResponse;
import com.bahaga.booking.dto.SalonResponse;
import com.bahaga.booking.model.Persona;
import com.bahaga.booking.model.Reservacion;
import com.bahaga.booking.model.Salon;
import com.bahaga.booking.repository.PersonaRepository;
import com.bahaga.booking.repository.ReservacionRepository;
import com.bahaga.booking.repository.SalonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public ReservacionResponse createReservacion(ReservacionDTO reservacionDTO, String correo,String password) {

        //Verifica las credenciales del usuario
        Optional<Persona> personaOptional=personaService.login(correo, password);
        if (personaOptional.isEmpty()){
            throw new IllegalArgumentException("Credenciales inválidas, no se puede realizar reservación");
        }
        Persona persona= personaOptional.get();

        // Buscar el salón por su ID
        Salon salon = salonRepository.findById(reservacionDTO.salonId())
                .orElseThrow(() -> new IllegalArgumentException("Salón no encontrado con ID: " + reservacionDTO.salonId()));

            // Buscar la persona (funcionaba para pruebas)
        /*Persona persona = personaRepository.findById(reservacionDTO.personaId())
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada con ID: " + reservacionDTO.personaId()));*/

        // Crear la reservación y asignar el salón
        Reservacion reservacion = new Reservacion();
        reservacion.setFechaEvento(reservacionDTO.fechaEvento());
        reservacion.setHora(reservacionDTO.hora());
        reservacion.setSalon(salon);  // Asocia el salón encontrado
        reservacion.setPersona(persona);
        reservacion.setTipoEvento(reservacionDTO.tipoEvento());
        reservacion.setCantidadPersonas(reservacionDTO.cantidadPersonas());
        reservacion.setObservaciones(reservacionDTO.observaciones());


        // aqui iria la logica para implementar el total cuando se inclyen productos

        // Guardar la reservación en la base de datos
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
                salon.getSalonId(),
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
    }
}
