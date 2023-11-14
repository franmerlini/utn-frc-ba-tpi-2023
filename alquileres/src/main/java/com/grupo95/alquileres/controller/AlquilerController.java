package com.grupo95.alquileres.controller;

import com.grupo95.alquileres.entity.AlquilerEntity;
import com.grupo95.alquileres.entity.request.AlquilerFinalizadoRequest;
import com.grupo95.alquileres.entity.response.FinalizarAlquilerDTO;
import com.grupo95.alquileres.service.AlquilerService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/alquileres")
@RequiredArgsConstructor
public class AlquilerController {
    private final AlquilerService alquilerService;

    @GetMapping()
    public ResponseEntity<List<AlquilerEntity>> obtenerAlquileres() {
        List<AlquilerEntity> response;
        try {
            response = alquilerService.obtenerAlquileres();
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Agregar Alquiler",
            description = "Agregar un nuevo alquiler con la estación de retiro especificada."
    )
    public ResponseEntity<String> agregarAlquiler(@RequestParam int estacionRetiro) {
        try {
            alquilerService.agregarAlquiler(estacionRetiro);
            return ResponseEntity.status(HttpStatus.CREATED).body("Alquiler agregado exitosamente.");
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La estación de retiro especificada no existe.");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parámetros de solicitud no válidos.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Se produjo un error interno al agregar el alquiler.");
        }
    }

    @PutMapping("/finalizar")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Finalizar el alquiler",
            description = "Terminar el alquiler en curso."
    )
    public ResponseEntity<?> finalizarAlquiler(@RequestBody AlquilerFinalizadoRequest alquilerFinalizado) {
        FinalizarAlquilerDTO response;
        try {
            response = alquilerService.finalizarAlquilerConMoneda(alquilerFinalizado.getId(), alquilerFinalizado.getLatitud(), alquilerFinalizado.getLongitud(), alquilerFinalizado.getMoneda());
            return ResponseEntity.ok(response);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("null");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}






