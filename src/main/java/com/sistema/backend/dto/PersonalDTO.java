package com.sistema.backend.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class PersonalDTO {
    private Long id;
    private String codigo;
    private String nombres;
    private String apellidos;
    private String dni;
    private String telefono;
    private String correoInstitucional;
    private String correoPersonal;
    private String direccion;
    private LocalDate fechaNacimiento;
    private String sexo;
    private String fotoUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    // getters y setters
}
