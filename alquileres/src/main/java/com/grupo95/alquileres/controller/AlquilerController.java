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
    public void agregarAlquiler(@RequestParam int estacionRetiro) {
        alquilerservice.agregarAlquiler(estacionRetiro);
    }

    @PutMapping("/finalizar")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Finalizar el alquiler", description = "Terminar el alquiler en curso."
    )
    public ResponseEntity<FinalizarAlquilerDTO> finalizarAlquiler(@RequestBody AlquilerFinalizadoRequest alquilerFinalizado) throws Exception {
        FinalizarAlquilerDTO response;
        try {
            response = alquilerservice.finalizarAlquilerConMoneda(alquilerFinalizado.getId(), alquilerFinalizado.getLatitud(), alquilerFinalizado.getLongitud(), alquilerFinalizado.getMoneda());
        } catch (NoSuchElementException ex) {
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        } catch (Exception ex) {
            System.out.println(ex);
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(response);
    }

}
