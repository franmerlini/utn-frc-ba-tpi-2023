package com.grupo95.alquileres.entity.request;

import jakarta.annotation.Nullable;
import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Data
@Getter
public class AlquilerFinalizadoRequest {
    private int id;
    private double latitud;
    private double longitud;
    @Nullable
    private String moneda;
}
