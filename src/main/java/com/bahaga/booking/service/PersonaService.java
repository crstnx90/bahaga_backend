package com.bahaga.booking.service;

import com.bahaga.booking.dto.PersonaDTO;
import com.bahaga.booking.model.Persona;
import com.bahaga.booking.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    // Guardar o crear una nueva persona
    public Persona createPersona(PersonaDTO personaDTO) {
        // Crear la persona a partir del DTO
        Persona persona = new Persona(personaDTO);

        // Si el campo admin es null, se asigna false por defecto (para evitar valores nulos)
        if (personaDTO.admin() == null) {
            persona.setAdmin(false);  // Valor por defecto si no se especifica
        }

        // Si se proporciona una contraseña, se asigna tal cual (sin encriptación)
        if (personaDTO.password() != null && !personaDTO.password().isEmpty()) {
            persona.setPassword(personaDTO.password());
        }

        // Guardar la persona en la base de datos
        return personaRepository.save(persona);
    }

    // Obtener todas las personas
    public List<Persona> getAllPersonas() {
        return personaRepository.findAll();
    }

    // Obtener una persona por ID
    public Optional<Persona> getById(Long id) {
        return personaRepository.findById(id);
    }

    // Obtener una persona por correo
    public Optional<Persona> getByCorreo(String correo) {
        return personaRepository.findByCorreo(correo);
    }

    // Eliminar una persona
    public void deletePersona(Long id) {
        personaRepository.deleteById(id);
    }

    // Actualizar una persona (en caso de que quieras modificar sus datos)
    public Persona updatePersona(Long id, PersonaDTO personaDTO) {
        // Buscar la persona por ID
        Optional<Persona> optionalPersona = personaRepository.findById(id);

        if (optionalPersona.isPresent()) {
            Persona persona = optionalPersona.get();

            // Actualizar los datos de la persona con el DTO
            persona.setTipoDocumento(personaDTO.tipoDocumento());
            persona.setNumeroId(personaDTO.numeroId());
            persona.setNombres(personaDTO.nombres());
            persona.setApellidos(personaDTO.apellidos());
            persona.setDireccion(personaDTO.direccion());
            persona.setPais(personaDTO.pais());
            persona.setCiudad(personaDTO.ciudad());
            persona.setFechaNacimiento(personaDTO.fechaNacimiento());
            persona.setSexo(personaDTO.sexo());
            persona.setCorreo(personaDTO.correo());
            persona.setTelefono(personaDTO.telefono());

            // Solo actualizar la contraseña si se proporciona una nueva
            if (personaDTO.password() != null && !personaDTO.password().isEmpty()) {
                persona.setPassword(personaDTO.password()); // Ya no se codifica la contraseña
            }

            // Asignar el valor de admin solo si se proporciona
            if (personaDTO.admin() != null) {
                persona.setAdmin(personaDTO.admin());
            }

            // Guardar la persona actualizada
            return personaRepository.save(persona);
        } else {
            // Si no se encuentra la persona, lanzar una excepción o retornar null
            throw new IllegalArgumentException("Persona no encontrada");
        }
    }

    // Metodo para iniciar sesion
    public Optional<Persona> login(String correo, String password) {
        // Busca por correo
        Optional<Persona> persona = personaRepository.findByCorreo(correo);

        // Verifica si la persona existe y si la contraseña coincide
        if (persona.isPresent() && persona.get().getPassword().equals(password)) {
            return persona; // Credenciales correctas
        }
        return Optional.empty(); // Credenciales incorrectas
    }

    //Metodo para buscar correo y recuperar contraseña(simulado)
    public Optional<Persona> recoverWithCorreo(String correo){
        //BUsca por correo
        Optional<Persona> persona=personaRepository.findByCorreo(correo);

        //Verifica si el correo existe
        if (persona.isPresent()&& persona.get().getCorreo().equals(correo)){
            return persona; // Correo correcto
        }
        return Optional.empty(); // Credenciales incorrectas
    }

}
