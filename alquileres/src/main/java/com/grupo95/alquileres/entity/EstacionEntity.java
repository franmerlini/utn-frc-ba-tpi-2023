package com.grupo95.alquileres.entity;

import jakarta.persistence.Entity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class EstacionEntity {
    private int id;
    private String nombre;
    private LocalDateTime fechaHoraCreacion;
    private double latitud;
    private double longitud;

}
