package com.grupo95.alquileres.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "TARIFAS", schema = "main")
@Getter
public class TarifaEntity {

    @Id
    @Column(name = "ID")
    private Integer id;
    @Column(name = "TIPO_TARIFA")
    private Integer tipoTarifa;
    @Column(name = "DEFINICION")
    private String definicion;
    @Column(name = "DIA_SEMANA")
    private Integer diaSemana;
    @Column(name = "DIA_MES")
    private Integer diaMes;
    @Column(name = "MES")
    private Integer mes;
    @Column(name = "ANIO")
    private Integer anio;
    @Column(name = "MONTO_FIJO_ALQUILER")
    private float montoFijoAlquiler;
    @Column(name = "MONTO_MINUTO_FRACCION")
    private float montoMinutoFraccion;
    @Column(name = "MONTO_KM")
    private float montoKM;
    @Column(name = "MONTO_HORA")
    private float montoHora;


}
