package com.grupo95.estaciones.service;

import com.grupo95.estaciones.entity.EstacionEntity;
import com.grupo95.estaciones.repository.EstacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstacionService {
    private final EstacionRepository estacionRepository;

    @Autowired
    public EstacionService(EstacionRepository estacionRepository) {
        this.estacionRepository = estacionRepository;
    }

    public List<EstacionEntity> obtenerEstaciones() {
        return estacionRepository.findAll();
    }

    public void agregarEstacion(EstacionEntity estacion) {
        System.out.println(estacion);
        estacionRepository.insertarEstacion(estacion.getFechaHoraCreacion(), estacion.getLatitud(), estacion.getLongitud(), estacion.getNombre());
    }
}