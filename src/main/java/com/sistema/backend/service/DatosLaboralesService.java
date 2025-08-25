package com.sistema.backend.service;

import com.sistema.backend.entity.DatosLaborales;
import com.sistema.backend.repository.DatosLaboralesRepository;
import com.sistema.backend.repository.PersonalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DatosLaboralesService {

    private final DatosLaboralesRepository datosLaboralesRepository;
    private final PersonalRepository personalRepository;

    public DatosLaboralesService(DatosLaboralesRepository datosLaboralesRepository, PersonalRepository personalRepository) {
        this.datosLaboralesRepository = datosLaboralesRepository;
        this.personalRepository = personalRepository;
    }

    public List<DatosLaborales> getAll() {
        return datosLaboralesRepository.findAll();
    }

    public Optional<DatosLaborales> getById(UUID id) {
        return datosLaboralesRepository.findById(id);
    }

    public List<DatosLaborales> getByPersonalId(UUID personalId) {
        return personalRepository.findById(personalId)
                .map(datosLaboralesRepository::findByPersonal)
                .orElse(List.of());
    }

    public DatosLaborales save(DatosLaborales datosLaborales) {
        return datosLaboralesRepository.save(datosLaborales);
    }

    public void delete(UUID id) {
        datosLaboralesRepository.deleteById(id);
    }
}
