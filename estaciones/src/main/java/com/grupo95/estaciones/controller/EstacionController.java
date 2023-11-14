package com.grupo95.estaciones.controller;

import com.grupo95.estaciones.entity.DTO.StationDTO;
import com.grupo95.estaciones.entity.EstacionEntity;
import com.grupo95.estaciones.service.EstacionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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
    @Operation(
            summary = "Obtiene todas las estaciones",
            description = "Muestra todas las estaciones que estan cargadas en la base de datos"
    )
    public ResponseEntity<List<EstacionEntity>> obtenerEstaciones() {
        List<EstacionEntity> response;
        try{
            response = _estacionService.obtenerEstaciones();
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Consigue la estacion mas cercana por Cordenadas",
            description = "Muestra la estacion mas cercana basandose en la latitud y longitud dada."
    )
    @GetMapping("/{lat}/{lng}")
    public ResponseEntity<?> GetStationByCoordinates(@PathVariable Double lat, @PathVariable Double lng){
        EstacionEntity response;
        try {
            response = _estacionService.getStationByCoordinates(lat, lng);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Las coordenadas proporcionadas no son válidas.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Se produjo un error interno en el servicio.");
        }
        return ResponseEntity.ok(response);
    }
    @Operation(
            summary = "Crea o actualiza una estación",
            description = "Guarda los detalles de la estación."
    )
    @PostMapping()
    //TODO Fix DTO validation
    public ResponseEntity<EstacionEntity> saveStation(@Valid @RequestBody StationDTO station) {
        EstacionEntity response;
        try{
            response = _estacionService.saveStation(station);
        } catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtiene una estación por una ID",
            description = "Usando una ID busca una estacion en la base de datos"
    )
    public ResponseEntity<EstacionEntity> obtenerEstacionPorId(@PathVariable Integer id) {
        EstacionEntity response;
        try{
            response = _estacionService.obtenerEstacionPorId(id);
        }catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        return ResponseEntity.ok(response);
    }
}
