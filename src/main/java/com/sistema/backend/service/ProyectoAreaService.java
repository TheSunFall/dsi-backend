package com.sistema.backend.service;

import com.sistema.backend.entity.ProyectoArea;
import com.sistema.backend.repository.ProyectoAreaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProyectoAreaService {

    private final ProyectoAreaRepository proyectoAreaRepository;

    public ProyectoAreaService(ProyectoAreaRepository proyectoAreaRepository) {
        this.proyectoAreaRepository = proyectoAreaRepository;
    }

    public List<ProyectoArea> getAll() {
        return proyectoAreaRepository.findAll();
    }

    public List<ProyectoArea> getByProyectoId(UUID proyectoId) {
        return proyectoAreaRepository.findByProyectoId(proyectoId);
    }

    public Optional<ProyectoArea> getById(UUID id) {
        return proyectoAreaRepository.findById(id);
    }

    public ProyectoArea save(ProyectoArea proyectoArea) {
        return proyectoAreaRepository.save(proyectoArea);
    }

    public void delete(UUID id) {
        proyectoAreaRepository.deleteById(id);
    }
}
