package co.gov.biblored.registro.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.gov.biblored.registro.model.Persona;

public interface PersonaRepository extends CrudRepository<Persona, Long>{

    @Query(value = "SELECT * FROM Persona p WHERE p.num_documento = ?1", nativeQuery = true)
	public Persona buscarPersonaNative(Long id);
    
}
