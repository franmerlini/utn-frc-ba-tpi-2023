package com.grupo95.estaciones.controller;

import com.grupo95.estaciones.entity.EstacionEntity;
import com.grupo95.estaciones.service.EstacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estaciones")
@RequiredArgsConstructor
public class EstacionController {
    private final EstacionService estacionService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<EstacionEntity> obtenerEstaciones() {
        return estacionService.obtenerEstaciones();
    }

    // TODO: Add guard conditions
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void agregarEstacion(@RequestBody EstacionEntity estacion) {
        estacionService.agregarEstacion(estacion);
    }
}
