package com.grupo95.alquileres.entity.request;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class AlquilerFinalizadoResponse {
    private int id;
    private double latitud;
    private double longitud;
    private String moneda;
}
