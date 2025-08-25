package com.sistema.backend.service;

import com.sistema.backend.entity.Personal;
import com.sistema.backend.repository.PersonalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonalService {

    private final PersonalRepository personalRepository;

    public PersonalService(PersonalRepository personalRepository) {
        this.personalRepository = personalRepository;
    }

    public List<Personal> getAll() {
        return personalRepository.findAll();
    }

    public Optional<Personal> getById(UUID id) {
        return personalRepository.findById(id);
    }

    public Personal save(Personal personal) {
        return personalRepository.save(personal);
    }

    public void delete(UUID id) {
        personalRepository.deleteById(id);
    }
}
