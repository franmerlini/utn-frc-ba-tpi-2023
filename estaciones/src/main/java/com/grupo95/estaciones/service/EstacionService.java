package com.grupo95.estaciones.service;

import com.grupo95.estaciones.entity.EstacionEntity;
import com.grupo95.estaciones.repository.EstacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstacionService {
    private final EstacionRepository _estacionRepository;

    public List<EstacionEntity> obtenerEstaciones(){
        return _estacionRepository.findAll();
    }

    public void agregarEstacion(EstacionEntity estacion) {
        System.out.println(estacion);
        _estacionRepository.insertarEstacion(estacion.getFechaHoraCreacion(), estacion.getLatitud(), estacion.getLongitud(), estacion.getNombre());
    }
}