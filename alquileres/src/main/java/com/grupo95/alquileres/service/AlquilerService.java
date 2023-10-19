package com.grupo95.alquileres.service;

import com.grupo95.alquileres.entity.AlquilerEntity;
import com.grupo95.alquileres.repository.AlquilerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlquilerService {
    private final AlquilerRepository alquilerRepository;

    @Autowired
    public AlquilerService(AlquilerRepository alquilerRepository) {
        this.alquilerRepository = alquilerRepository;
    }

    public List<AlquilerEntity> obtenerAlquileres() {
        return alquilerRepository.findAll();
    }

    public void agregarAlquiler(AlquilerEntity alquiler) {
        System.out.println(alquiler);
        alquilerRepository.insertarAlquiler(alquiler.getEstado(), alquiler.getEstacionRetiro(), alquiler.getEstacionDevolucion(), alquiler.getFechaHoraRetiro(), alquiler.getFechaHoraDevolucion(), alquiler.getMonto(), alquiler.getTarifa());
    }
}