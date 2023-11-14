package com.grupo95.alquileres.controller;

import com.grupo95.alquileres.entity.AlquilerEntity;
import com.grupo95.alquileres.entity.request.AlquilerFinalizadoRequest;
import com.grupo95.alquileres.entity.request.NuevoAlquilerRequest;
import com.grupo95.alquileres.entity.response.FinalizarAlquilerDTO;
import com.grupo95.alquileres.service.AlquilerService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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
    @Operation(summary = "Obtiene todos los alquileres")
    public ResponseEntity<List<AlquilerEntity>> obtenerAlquileres(@RequestParam(name = "cliente", required = false) String cliente, @RequestParam(name = "estado", required = false) Integer estado, @RequestParam(name = "tarifa", required = false) Integer tarifa) {
        List<AlquilerEntity> response;
        try {
            response = alquilerService.obtenerAlquileres(cliente, estado, tarifa);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            System.out.println(ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Agregar Alquiler",
            description = "Agregar un nuevo alquiler con la estación de retiro especificada."
    )
    public ResponseEntity<String> agregarAlquiler(@RequestBody @Valid NuevoAlquilerRequest request) {
        try {
            alquilerService.agregarAlquiler(request.getCliente(), request.getEstacionRetiro());
            return ResponseEntity.status(HttpStatus.CREATED).body("Alquiler agregado exitosamente.");
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parámetros de solicitud no válidos. \n" + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Se produjo un error interno al agregar el alquiler.\n" + ex.getMessage());
        }
    }

    @PutMapping("/finalizar")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
            summary = "Finalizar el alquiler",
            description = "Terminar el alquiler en curso.\n" +
                    "Monedas Soportadas: [\n" +
                    "\t EUR\n," +
                    "\t CLP\n," +
                    "\t BRL\n," +
                    "\t COP\n," +
                    "\t PEN\n," +
                    "\t GBP\n," +
                    "\t USD.]"
    )
    public ResponseEntity<?> finalizarAlquiler(@RequestBody @Valid AlquilerFinalizadoRequest alquilerFinalizado) {
        FinalizarAlquilerDTO response;
        try {
            response = alquilerService.finalizarAlquilerConMoneda(alquilerFinalizado.getId(), alquilerFinalizado.getLatitud(), alquilerFinalizado.getLongitud(), alquilerFinalizado.getMoneda());
            return ResponseEntity.ok(response);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parámetros de solicitud no válidos. \n" + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Se produjo un error interno al finalizar el alquiler.");
        }
    }
}






