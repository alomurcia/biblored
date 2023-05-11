package co.gov.biblored.registro.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.gov.biblored.registro.dto.RolDto;
import co.gov.biblored.registro.repository.RolRepository;

@Service
public class RolService {
    @Autowired
    private RolRepository rolRepository;

    public Iterable<RolDto> findAll() {
        ArrayList<RolDto> roles = new ArrayList<RolDto>();
        rolRepository.findAll().forEach(rol -> {
            roles.add(new RolDto(rol.getId(), rol.getNombre()));
        });
        return roles;
    }
}
