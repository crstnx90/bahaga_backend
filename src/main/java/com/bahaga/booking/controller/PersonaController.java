package com.bahaga.booking.controller;

import com.bahaga.booking.dto.*;
import com.bahaga.booking.model.Persona;
import com.bahaga.booking.service.JwtUtil;
import com.bahaga.booking.service.PersonaService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @Autowired
    private JwtUtil jwtUtil;


    // Crear una nueva persona
    @PostMapping
    public ResponseEntity<Persona> createPersona(@RequestBody PersonaDTO personaDTO) {
        Persona nuevaPersona = personaService.createPersona(personaDTO);
        return new ResponseEntity<>(nuevaPersona, HttpStatus.CREATED);
    }

    // Obtener todas las personas
    @GetMapping
    public ResponseEntity<List<PersonaResponse>> getAllPersonas() {
        List<Persona> personas = personaService.getAllPersonas();

        // Convertir la lista de Persona a PersonaResponse
        List<PersonaResponse> personaResponses = personas.stream().map(persona ->
                new PersonaResponse(
                        persona.getId(),
                        persona.getTipoDocumento(),
                        persona.getNumeroId(),
                        persona.getNombres(),
                        persona.getApellidos(),
                        persona.getPais(),
                        persona.getCiudad(),
                        persona.getCorreo(),
                        persona.getTelefono()
                )
        ).toList();

        return new ResponseEntity<>(personaResponses, HttpStatus.OK);
    }


    // Obtener persona por ID
    @GetMapping("/{id}")
    public ResponseEntity<PersonaResponse> getPersonaById(@PathVariable Long id) {
        Optional<Persona> persona = personaService.getById(id);
        if (persona.isPresent()) {
            Persona p = persona.get();

            // Convertir a PersonaResponse
            PersonaResponse personaResponse = new PersonaResponse(
                    p.getId(),
                    p.getTipoDocumento(),
                    p.getNumeroId(),
                    p.getNombres(),
                    p.getApellidos(),
                    p.getPais(),
                    p.getCiudad(),
                    p.getCorreo(),
                    p.getTelefono()
            );

            return new ResponseEntity<>(personaResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Si no se encuentra la persona
        }
    }

    // Eliminar persona por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersona(@PathVariable Long id) {
        personaService.deletePersona(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }

    // Actualizar persona por ID
    @PutMapping("/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable Long id, @RequestBody PersonaDTO personaDTO) {
        Persona personaActualizada = personaService.updatePersona(id, personaDTO);
        if (personaActualizada != null) {
            return new ResponseEntity<>(personaActualizada, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Si no se encuentra la persona
        }
    }

    // Realizar login de una cuenta creada y devolver un jwt
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequest loginRequest) {
        Optional<Persona> persona = personaService.login(loginRequest.correo(), loginRequest.password());

        if (persona.isPresent()) {
            // Generar el token JWT
            String token = jwtUtil.generateToken(persona.get().getCorreo());

            // Devolver el token al cliente
            return ResponseEntity.ok(new LoginResponse(true, "Inicio de sesión exitoso", token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse(false, "Credenciales inválidas", null));
        }
    }

    @PostMapping("/recuperar")
    public ResponseEntity<?> recuperarCuenta(@RequestBody @Valid RecuperarRequest recuperarRequest) {
        Optional<Persona> persona = personaService.recoverWithCorreo(recuperarRequest.correo());

        if (persona.isPresent()) {
            // Simula el envío de correo
            return ResponseEntity.ok(new RecuperarResponse(true, "Revise su correo registrado para recuperar su cuenta."));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new RecuperarResponse(false, "El correo ingresado no está registrado."));
        }
    }

    // Método para mostrar información del perfil usando el token
    @GetMapping("/perfil")
    public ResponseEntity<?> obtenerPerfil(@RequestHeader("Authorization") String token) {
        try {
            // Validar el token
            String correoUsuario = jwtUtil.validateToken(token.replace("Bearer ", ""));

            Optional<Persona> personaOptional = personaService.getByCorreo(correoUsuario);
            if (personaOptional.isPresent()) {
                Persona persona = personaOptional.get();

                // Crear una respuesta de tipo PerfilResponse
                PerfilResponse perfilResponse = new PerfilResponse(
                        persona.getTipoDocumento(),
                        persona.getNumeroId(),
                        persona.getNombres(),
                        persona.getApellidos(),
                        persona.getPais(),
                        persona.getCiudad(),
                        persona.getCorreo(),
                        persona.getTelefono()
                );

                return ResponseEntity.ok(perfilResponse);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido o expirado");
        }
    }


}
