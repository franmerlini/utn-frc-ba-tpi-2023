package com.grupo95.alquileres.controller;

import com.grupo95.alquileres.entity.AlquilerEntity;
import com.grupo95.alquileres.service.AlquilerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alquileres")
@RequiredArgsConstructor
public class AlquilerController {
    private final AlquilerService alquilerservice;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<AlquilerEntity> obtenerAlquileres() {
        return alquilerservice.obtenerAlquileres();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "LA DE TU MAMA",
            description = "LA DE TU ABUELA"
    )
    public void agregarAlquiler(@RequestBody AlquilerEntity alquiler) {
        alquilerservice.agregarAlquiler(alquiler);
    }
}
