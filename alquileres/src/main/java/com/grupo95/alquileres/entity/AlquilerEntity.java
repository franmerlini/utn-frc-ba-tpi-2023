package com.grupo95.alquileres.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "ALQUILERES", schema = "main")
public class AlquilerEntity {
    @Basic
    @Column(name = "ESTADO")
    int estado;
    @Basic
    @Column(name = "ESTACION_RETIRO")
    int estacionRetiro;
    @Basic
    @Column(name = "ESTACION_DEVOLUCION")
    int estacionDevolucion;
    @Basic
    @Column(name = "FECHA_HORA_RETIRO")
    LocalDateTime fechaHoraRetiro;
    @Basic
    @Column(name = "FECHA_HORA_DEVOLUCION")
    LocalDateTime fechaHoraDevolucion;
    @Basic
    @Column(name = "MONTO")
    float monto;
    @Basic
    @Column(name = "ID_TARIFA")
    int tarifa;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;

}
