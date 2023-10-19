package com.grupo95.alquileres.controller;

import com.grupo95.alquileres.entity.AlquilerEntity;
import com.grupo95.alquileres.service.AlquilerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AlquilerController {
    private final AlquilerService alquilerservice;

    @Autowired
    public AlquilerController(AlquilerService alquilerservice) {
        this.alquilerservice = alquilerservice;
    }

    @GetMapping("/alquiler")
    public List<AlquilerEntity> obtenerAlquileres() {
        System.out.println("AlquilerController.obtenerAlquileres()");
        return alquilerservice.obtenerAlquileres();
    }

    @PostMapping("/alquiler")
    @Operation(
            summary = "LA DE TU MAMA",
            description = "LA DE TU ABUELA"
    )
    public String agregarAlquiler(@RequestBody AlquilerEntity alquiler) {
        System.out.println("AlquilerController.agregarAlquiler()");
        alquilerservice.agregarAlquiler(alquiler);
        return "";
    }
}
