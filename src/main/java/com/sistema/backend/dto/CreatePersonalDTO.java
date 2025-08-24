package com.sistema.backend.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class CreatePersonalDTO {

    @NotBlank
    @Size(max = 100)
    private String nombres;

    @NotBlank
    @Size(max = 100)
    private String apellidos;

    @NotBlank
    @Pattern(regexp = "\\d{8}", message = "El DNI debe tener 8 dígitos")
    private String dni;

    @Pattern(regexp = "\\d{9}", message = "El teléfono debe tener 9 dígitos")
    private String telefono;

    @Email
    private String correoPersonal;

    private String direccion;
    private String sexo;

    // getters y setters
}

