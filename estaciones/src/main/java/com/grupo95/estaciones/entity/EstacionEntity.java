package com.grupo95.estaciones.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Data
@Table(name = "ESTACIONES", schema = "main")
public class EstacionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;
    @Basic
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic
    @Column(name = "FECHA_HORA_CREACION")
    private LocalDateTime fechaHoraCreacion;
    @Basic
    @Column(name = "LATITUD")
    private Float latitud;
    @Basic
    @Column(name = "LONGITUD")
    private Float longitud;

    public double getDistance(double latitude, double longitude){
        return Math.sqrt(Math.pow(latitude - this.getLatitud(), 2) + Math.pow(longitude - this.getLongitud(), 2));
    }
}
