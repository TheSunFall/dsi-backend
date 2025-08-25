package com.sistema.backend.service;

import com.sistema.backend.entity.Proyecto;
import com.sistema.backend.repository.ProyectoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProyectoService {

    private final ProyectoRepository proyectoRepository;

    public ProyectoService(ProyectoRepository proyectoRepository) {
        this.proyectoRepository = proyectoRepository;
    }

    public List<Proyecto> getAll() {
        return proyectoRepository.findAll();
    }

    public Optional<Proyecto> getById(UUID id) {
        return proyectoRepository.findById(id);
    }

    public Optional<Proyecto> getByCodigo(String codigo) {
        return proyectoRepository.findByCodigo(codigo);
    }

    public Proyecto save(Proyecto proyecto) {
        return proyectoRepository.save(proyecto);
    }

    public void delete(UUID id) {
        proyectoRepository.deleteById(id);
    }
}
