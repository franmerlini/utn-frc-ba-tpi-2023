package com.grupo95.alquileres.entity.response;

import lombok.Data;
import lombok.Getter;

import java.time.Duration;

@Data
@Getter
public class FinalizarAlquilerDTO {
    private final String estacionSalida;
    private final String estacionLlegada;
    private final Long duracionAlquiler;
    private final Double montoTotal;
    private final Double distancia;
    private final String moneda;

}
