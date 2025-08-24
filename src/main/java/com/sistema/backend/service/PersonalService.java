package com.sistema.backend.service;

import com.sistema.backend.dto.CreatePersonalDTO;
import com.sistema.backend.dto.PersonalDTO;
import com.sistema.backend.entity.Personal;
import com.sistema.backend.repository.PersonalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonalService {

    private final PersonalRepository personalRepository;

    public PersonalService(PersonalRepository personalRepository) {
        this.personalRepository = personalRepository;
    }

    public List<PersonalDTO> findAll() {
        return personalRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public PersonalDTO findById(Long id) {
        Personal personal = personalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personal no encontrado"));
        return toDTO(personal);
    }

    public PersonalDTO create(CreatePersonalDTO dto) {
        Personal personal = new Personal();
        personal.setCodigo("EMP" + System.currentTimeMillis()); // código temporal
        personal.setNombres(dto.getNombres());
        personal.setApellidos(dto.getApellidos());
        personal.setDni(dto.getDni());
        personal.setTelefono(dto.getTelefono());
        personal.setCorreoPersonal(dto.getCorreoPersonal());
        personal.setDireccion(dto.getDireccion());
        personal.setSexo(dto.getSexo());

        Personal saved = personalRepository.save(personal);
        return toDTO(saved);
    }

    public void delete(Long id) {
        personalRepository.deleteById(id);
    }

    private PersonalDTO toDTO(Personal personal) {
        PersonalDTO dto = new PersonalDTO();
        dto.setId(personal.getId());
        dto.setCodigo(personal.getCodigo());
        dto.setNombres(personal.getNombres());
        dto.setApellidos(personal.getApellidos());
        dto.setDni(personal.getDni());
        dto.setTelefono(personal.getTelefono());
        dto.setCorreoInstitucional(personal.getCorreoInstitucional());
        dto.setCorreoPersonal(personal.getCorreoPersonal());
        dto.setDireccion(personal.getDireccion());
        dto.setFechaNacimiento(personal.getFechaNacimiento());
        dto.setSexo(personal.getSexo());
        dto.setFotoUrl(personal.getFotoUrl());
        dto.setCreatedAt(personal.getCreatedAt());
        dto.setUpdatedAt(personal.getUpdatedAt());
        return dto;
    }
}
