package co.gov.biblored.registro.dto;

import java.util.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDto {
    private Long id;
    @NotBlank
	@Size(min=2, max=150)
    private String nombre;
    @NotBlank
    @Email(message = "Please enter a valid e-mail address")
    private String email;
    private Date fechaRegistro;
    @NonNull
    private Long numDocumento;
    private Long idRol;
}
