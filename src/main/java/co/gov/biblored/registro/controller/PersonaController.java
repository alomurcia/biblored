package co.gov.biblored.registro.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.gov.biblored.registro.dto.PersonaDto;
import co.gov.biblored.registro.model.Persona;
import co.gov.biblored.registro.service.PersonaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("persona")
@CrossOrigin(origins = "*")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @GetMapping
    public ResponseEntity<Iterable<PersonaDto>> getPersonas() {
        return new ResponseEntity<Iterable<PersonaDto>>(this.personaService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonaDto> registrar(@Valid @RequestBody PersonaDto personaDto) throws IllegalAccessException { 
        this.personaService.registrar(personaDto);
        return new ResponseEntity<PersonaDto>(personaDto, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PersonaDto> actualizar(@Valid @RequestBody PersonaDto personaDto) {
        personaService.actualizar(personaDto, personaDto.getNumDocumento());
        return new ResponseEntity<PersonaDto>(personaDto, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        this.personaService.eliminar(id);
        return new ResponseEntity<String>(HttpStatus.OK);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(
     MethodArgumentNotValidException ex) {
    
    return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
}
}
