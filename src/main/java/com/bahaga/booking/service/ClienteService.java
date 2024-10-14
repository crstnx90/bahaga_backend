package com.bahaga.booking.service;


import com.bahaga.booking.model.Cliente;
import com.bahaga.booking.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

        //Crea y guarda un nuevo cliente
        public Cliente save(Cliente cliente){
            return clienteRepository.save(cliente);
        }

        //Obtener todos los clientes
        public List<Cliente> getAllClientes(){
            return clienteRepository.findAll();
        }

        //Obtener cliente por ID
        public Optional<Cliente> getClienteById(Long id){
            return clienteRepository.findById(id);
        }

        //Actualizar cliente
        public Cliente updateCliente(Long id, Cliente clienteDetalles){
            Optional<Cliente> clienteOptional=clienteRepository.findById(id);

            if(clienteOptional.isPresent()){
                Cliente cliente=clienteOptional.get();
                cliente.setNombres(clienteDetalles.getNombres());
                cliente.setApellidos(clienteDetalles.getApellidos());
                cliente.setDireccion(clienteDetalles.getDireccion());
                cliente.setCiudad(clienteDetalles.getCiudad());
                cliente.setCorreo(clienteDetalles.getCorreo());
                cliente.setTelefono(clienteDetalles.getTelefono());

                return clienteRepository.save(cliente);
            }
            return null;
        }

        //eliminar cliente
        public void deleteCliente(Long id){
            clienteRepository.deleteById(id);
        }

}
