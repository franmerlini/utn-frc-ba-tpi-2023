package com.grupo95.estaciones.controller;

import com.grupo95.estaciones.entity.EstacionEntity;
import com.grupo95.estaciones.service.EstacionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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

    @Operation(
            summary = "Get Nearest Station by Coordinates",
            description = "Retrieve the closest station based on provided latitude and longitude."
    )
    @GetMapping("/{lat}&{lng}")
    public ResponseEntity<EstacionEntity> GetStationByCoordinates(@PathVariable double lat, @PathVariable double lng){
        EstacionEntity response;
        try {
            response = _estacionService.getStationByCoordinates(lat, lng);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(response);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void agregarEstacion(@RequestBody EstacionEntity estacion) {
        _estacionService.agregarEstacion(estacion);
    }
}
