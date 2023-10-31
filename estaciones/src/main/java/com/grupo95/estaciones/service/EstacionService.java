package com.grupo95.estaciones.service;

import com.grupo95.estaciones.entity.EstacionEntity;
import com.grupo95.estaciones.repository.EstacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EstacionService {
    private final EstacionRepository _estacionRepository;

    public List<EstacionEntity> obtenerEstaciones(){
        return _estacionRepository.findAll();
    }
    public EstacionEntity getStationByCoordinates(double latitude, double longitude){
        if (Double.isNaN(latitude) || Double.isNaN(longitude)) throw new IllegalArgumentException();

        List<EstacionEntity> stations = _estacionRepository.findAll();
        double distance;

        EstacionEntity closestStation  = null;
        double minDistance  = Double.MAX_VALUE;

        for (EstacionEntity estacion : stations) {
            distance = estacion.getDistance(latitude, longitude);

            if (distance < minDistance ) {
                minDistance  = distance;
                closestStation  = estacion;
            }
        }
        return closestStation;
    }
    public void agregarEstacion(EstacionEntity estacion) {
        System.out.println(estacion);
        _estacionRepository.insertarEstacion(estacion.getFechaHoraCreacion(), estacion.getLatitud(), estacion.getLongitud(), estacion.getNombre());
    }
}