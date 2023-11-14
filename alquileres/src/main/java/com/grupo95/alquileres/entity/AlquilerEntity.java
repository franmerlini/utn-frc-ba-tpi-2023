package com.grupo95.alquileres.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Data
@Setter
@Getter
@Table(name = "ALQUILERES", schema = "main")
public class AlquilerEntity {
    @Basic
    @Column(name = "ESTADO")
    Integer estado;
    @Basic
    @Column(name = "ID_CLIENTE")
    String idCliente;
    @Basic
    @Column(name = "ESTACION_RETIRO")
    Integer estacionRetiro;
    @Basic
    @Column(name = "ESTACION_DEVOLUCION")
    Integer estacionDevolucion;
    @Basic
    @Column(name = "FECHA_HORA_RETIRO")
    LocalDateTime fechaHoraRetiro;
    @Basic
    @Column(name = "FECHA_HORA_DEVOLUCION")
    LocalDateTime fechaHoraDevolucion;
    @Basic
    @Column(name = "MONTO")
    Double monto;
    @Basic
    @Column(name = "ID_TARIFA")
    Integer tarifa;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Integer id;

    public boolean estaFinalizado(){
        return estado == 2;
    }
}
