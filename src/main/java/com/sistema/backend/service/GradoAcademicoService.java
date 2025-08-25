package com.sistema.backend.service;

import com.sistema.backend.entity.GradoAcademico;
import com.sistema.backend.repository.GradoAcademicoRepository;
import com.sistema.backend.repository.PersonalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GradoAcademicoService {

    private final GradoAcademicoRepository gradoAcademicoRepository;
    private final PersonalRepository personalRepository;

    public GradoAcademicoService(GradoAcademicoRepository gradoAcademicoRepository, PersonalRepository personalRepository) {
        this.gradoAcademicoRepository = gradoAcademicoRepository;
        this.personalRepository = personalRepository;
    }

    public List<GradoAcademico> getAll() {
        return gradoAcademicoRepository.findAll();
    }

    public Optional<GradoAcademico> getById(UUID id) {
        return gradoAcademicoRepository.findById(id);
    }

    public List<GradoAcademico> getByPersonalId(UUID personalId) {
        return personalRepository.findById(personalId)
                .map(gradoAcademicoRepository::findByPersonal)
                .orElse(List.of());
    }

    public GradoAcademico save(GradoAcademico gradoAcademico) {
        return gradoAcademicoRepository.save(gradoAcademico);
    }

    public void delete(UUID id) {
        gradoAcademicoRepository.deleteById(id);
    }
}
