package co.gov.biblored.registro.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.biblored.registro.dto.PersonaDto;
import co.gov.biblored.registro.model.Persona;
import co.gov.biblored.registro.model.Rol;
import co.gov.biblored.registro.repository.PersonaRepository;
import co.gov.biblored.registro.repository.RolRepository;

@Service
public class PersonaService {
    @Autowired
    private PersonaRepository personaRepository;
    @Autowired
    private RolRepository rolRepository;

    public Iterable<PersonaDto> findAll() {
        ArrayList<PersonaDto> personas = new ArrayList<PersonaDto>();
        this.personaRepository.findAll().forEach(persona -> {
            personas.add(
                new PersonaDto(persona.getId(), persona.getNombre(), 
                persona.getEmail(), persona.getFechaRegistro(), persona.getNumDocumento(), persona.getRol().getId())
            );
        });
        return personas;    
    }

    public void registrar(PersonaDto personaDto){
        Persona persona = new Persona();
        persona.setNombre(personaDto.getNombre());
        persona.setEmail(personaDto.getEmail());
        persona.setFechaRegistro(new Date());

        Persona personaExiste = buscarPersona(personaDto.getNumDocumento());
        if(personaExiste!=null){
            throw new IllegalArgumentException("Persona ya existe");
        }
        persona.setNumDocumento(personaDto.getNumDocumento());
        Optional<Rol> rol = this.rolRepository.findById(personaDto.getIdRol());
        if (!rol.isPresent()){  
            throw new IllegalArgumentException("Rol no existe");
        }
        persona.setRol(rol.get());
        this.personaRepository.save(persona);
        personaDto.setId(persona.getId());
    }

    public void actualizar(PersonaDto personaDto, long id){
        Persona persona=buscarPersona(id);
        persona.setNombre(personaDto.getNombre());
        persona.setEmail(personaDto.getEmail());
        persona.setFechaRegistro(new Date());
        persona.setNumDocumento(personaDto.getNumDocumento());
        Optional<Rol> rol = this.rolRepository.findById(personaDto.getIdRol());
        if (!rol.isPresent()){  
            throw new IllegalArgumentException("Rol no existe");
        }
        persona.setRol(rol.get());
        this.personaRepository.save(persona);
        personaDto.setId(persona.getId());
    }

    public void eliminar(Long idPersona){
        this.personaRepository.deleteById(idPersona);
    }

    public void actualizar(Long idPersona){
        this.personaRepository.deleteById(idPersona);
    }

    public Persona buscarPersona(Long numDocumento){
        return personaRepository.buscarPersonaNative(numDocumento);
    }
}

