package com.bahaga.booking.repository;

import com.bahaga.booking.model.Persona;
import org.hibernate.query.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    //Metodos particulares para ser manejados a nivel Persona
    Optional<Persona> findByCorreo(String correo);

}
