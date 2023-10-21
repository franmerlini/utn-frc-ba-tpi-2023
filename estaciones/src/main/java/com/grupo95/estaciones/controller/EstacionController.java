package com.grupo95.estaciones.controller;

import com.grupo95.estaciones.entity.EstacionEntity;
import com.grupo95.estaciones.service.EstacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EstacionController {
    private final EstacionService estacionService;

    @Autowired
    public EstacionController(EstacionService estacionService) {
        this.estacionService = estacionService;
    }

    @GetMapping("/estaciones")
    @ResponseStatus(HttpStatus.OK)
    public List<EstacionEntity> obtenerEstaciones() {
        return estacionService.obtenerEstaciones();
    }

    // TODO: Add guard conditions
    @PostMapping("/estaciones")
    public ResponseEntity<String> agregarEstacion(@RequestBody EstacionEntity estacion) {
        System.out.println("EstacionController.agregarEstacion()");
        estacionService.agregarEstacion(estacion);
        return ResponseEntity.ok("Estacion Añadida: " + estacion.toString());
    }
}
