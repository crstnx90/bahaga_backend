package com.bahaga.booking.service;

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

    //Guardar o crear personas
    public Persona save(Persona persona){
        return personaRepository.save(persona);
    }

    //Obtener todas las personas
    public List<Persona> getAllPersonas(){
        return personaRepository.findAll();
    }

    //Obtener personas por ID
    public Optional<Persona> getById(Long id){
        return personaRepository.findById(id);
    }

    //Actualizar una persona
    public Persona updatePersona(Long id, Persona personaDetalles){
        Optional<Persona> personaOptional= personaRepository.findById(id);

        if(personaOptional.isPresent()){
            Persona persona= personaOptional.get();
            persona.setNombres(personaDetalles.getNombres());
            persona.setApellidos(personaDetalles.getApellidos());
            persona.setDireccion(personaDetalles.getDireccion());
            persona.setCiudad(personaDetalles.getCiudad());
            persona.setCorreo(personaDetalles.getCorreo());
            persona.setTelefono(personaDetalles.getTelefono());

            return personaRepository.save(persona);
        }
        return null;
    }

    //ELiminar una persona
    public void deletePersona(Long id){
        personaRepository.deleteById(id);
    }


}
