package co.gov.biblored.registro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.gov.biblored.registro.dto.RolDto;
import co.gov.biblored.registro.service.RolService;

@RestController()
@RequestMapping("rol")
@CrossOrigin(origins = "*")
public class RolController {
    
    @Autowired
    private RolService rolService;

    @GetMapping()
    public ResponseEntity<Iterable<RolDto>> getRoles() {
        return new ResponseEntity<Iterable<RolDto>>(rolService.findAll(), HttpStatus.OK);
    }
}
