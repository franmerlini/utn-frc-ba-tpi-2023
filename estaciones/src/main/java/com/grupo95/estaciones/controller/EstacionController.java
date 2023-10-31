package com.grupo95.estaciones.controller;

import com.grupo95.estaciones.entity.EstacionEntity;
import com.grupo95.estaciones.service.EstacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estaciones")
@RequiredArgsConstructor
public class EstacionController {
    private final EstacionService _estacionService;

    @GetMapping()
    public ResponseEntity<List<EstacionEntity>> obtenerEstaciones() {
        List<EstacionEntity> response;
        try{
            response = _estacionService.obtenerEstaciones();
        }catch (Exception ex){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(response);
    }

    // TODO: Add guard conditions
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void agregarEstacion(@RequestBody EstacionEntity estacion) {
        _estacionService.agregarEstacion(estacion);
    }
}
