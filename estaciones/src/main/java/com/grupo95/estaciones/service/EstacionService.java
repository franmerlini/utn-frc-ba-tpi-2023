package com.grupo95.estaciones.service;

import com.grupo95.estaciones.entity.DTO.StationDTO;
import com.grupo95.estaciones.entity.EstacionEntity;
import com.grupo95.estaciones.repository.EstacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
    public EstacionEntity saveStation(StationDTO station) {
        int nextId = _estacionRepository.getMaxId() + 1;
        EstacionEntity entity = new EstacionEntity();
        entity.setId(nextId);
        entity.setNombre(station.getName());
        entity.setLatitud(station.getLatitude());
        entity.setLongitud(station.getLongitude());
        entity.setFechaHoraCreacion(LocalDateTime.now());
        _estacionRepository.save(entity);
        return entity;
    }

    public EstacionEntity obtenerEstacionPorId(Integer id) {
        return _estacionRepository.findById(id).orElseThrow();
    }
}