package com.sistema.backend.service;

import com.sistema.backend.entity.Personal;
import com.sistema.backend.repository.PersonalRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonalService {

    private final PersonalRepository personalRepository;

    public PersonalService(PersonalRepository personalRepository) {
        this.personalRepository = personalRepository;
    }

    public List<Personal> findAll() {
        return personalRepository.findAll();
    }

    public Personal findById(Long id) {
        return personalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Personal no encontrado"));
    }

    public Personal create(Personal personal) {
        // Reemplazar por un generador de código en Oracle
        personal.setCodigo("EMP" + System.currentTimeMillis());
        personal.setCorreoInstitucional(personal.getCodigo().toLowerCase() + "@empresa.pe");
        return personalRepository.save(personal);
    }

    public Personal update(Long id, Personal datos) {
        Personal existing = findById(id);
        existing.setNombres(datos.getNombres());
        existing.setApellidos(datos.getApellidos());
        existing.setDni(datos.getDni());
        existing.setTelefono(datos.getTelefono());
        existing.setCorreoPersonal(datos.getCorreoPersonal());
        existing.setDireccion(datos.getDireccion());
        existing.setSexo(datos.getSexo());
        existing.setFechaNacimiento(datos.getFechaNacimiento());
        return personalRepository.save(existing);
    }

    public void delete(Long id) {
        personalRepository.deleteById(id);
    }
}
