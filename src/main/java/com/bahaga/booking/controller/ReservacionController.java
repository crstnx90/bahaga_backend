package com.bahaga.booking.controller;

import com.bahaga.booking.dto.PersonaResponse;
import com.bahaga.booking.dto.ReservacionDTO;
import com.bahaga.booking.dto.ReservacionResponse;
import com.bahaga.booking.dto.SalonResponse;
import com.bahaga.booking.model.Reservacion;
import com.bahaga.booking.service.JwtUtil;
import com.bahaga.booking.service.ReservacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reservacion")
public class ReservacionController {

    @Autowired
    private ReservacionService reservacionService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/reservaciones")
    public ResponseEntity<List<ReservacionResponse>> getAllReservacionesByCorreo(
            @RequestHeader("Authorization") String token) {

        System.out.println("Token recibido: " + token);
        try {
            // Validar el token y extraer el correo
            String correoUsuario = jwtUtil.validateToken(token.replace("Bearer ", ""));

            // Obtener las reservaciones del usuario por correo
            List<Reservacion> reservaciones = reservacionService.getAllReservacionesByCorreo(correoUsuario);

            // Convertir las reservaciones a ReservacionResponse
            List<ReservacionResponse> reservacionResponses = reservaciones.stream().map(reservacion -> {
                PersonaResponse personaResponse = new PersonaResponse(
                        reservacion.getPersona().getId(),
                        reservacion.getPersona().getTipoDocumento(),
                        reservacion.getPersona().getNumeroId(),
                        reservacion.getPersona().getNombres(),
                        reservacion.getPersona().getApellidos(),
                        reservacion.getPersona().getPais(),
                        reservacion.getPersona().getCiudad(),
                        reservacion.getPersona().getCorreo(),
                        reservacion.getPersona().getTelefono()
                );

                SalonResponse salonResponse = new SalonResponse(
                        reservacion.getSalon().getNombreSalon(),
                        reservacion.getSalon().getCapacidad()
                );

                return new ReservacionResponse(
                        reservacion.getReservacionId(),
                        reservacion.getFechaEvento(),
                        reservacion.getHora(),
                        personaResponse,
                        salonResponse,
                        reservacion.getTipoEvento(),
                        reservacion.getCantidadPersonas(),
                        reservacion.getObservaciones()
                );
            }).toList();

            return new ResponseEntity<>(reservacionResponses, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Error relacionado con el correo no encontrado o datos inválidos
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Token inválido u otro error general
        }
    }

    @PostMapping("/crear")
    public ResponseEntity<ReservacionResponse> createReservacion(
            @RequestHeader("Authorization") String token,
            @RequestBody ReservacionDTO reservacionDTO) {

        try {
            // Validar el token y extraer el correo
            String correoUsuario = jwtUtil.validateToken(token.replace("Bearer ", ""));

            // Crear la reservación llamando al servicio
            ReservacionResponse reservacionResponse = reservacionService.createReservacion(reservacionDTO, correoUsuario);

            return new ResponseEntity<>(reservacionResponse, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Error en los datos enviados o reserva no válida
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Token inválido o error en el proceso
        }
    }
}


