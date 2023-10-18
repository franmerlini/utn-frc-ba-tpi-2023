package com.grupo95.estaciones.controller;

import com.grupo95.estaciones.entity.EstacionEntity;
import com.grupo95.estaciones.service.EstacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EstacionController {
    private final EstacionService estacionService;

    @Autowired
    public EstacionController(EstacionService estacionService) {
        this.estacionService = estacionService;
    }

    @GetMapping("/estaciones")
    public List<EstacionEntity> obtenerEstaciones() {
        System.out.println("EstacionController.obtenerEstaciones()");
        return estacionService.obtenerEstaciones();
    }
}
