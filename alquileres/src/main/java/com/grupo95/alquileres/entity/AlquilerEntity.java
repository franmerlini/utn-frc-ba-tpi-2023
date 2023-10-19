package com.grupo95.alquileres.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
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

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getEstacionRetiro() {
        return estacionRetiro;
    }

    public void setEstacionRetiro(int estacionRetiro) {
        this.estacionRetiro = estacionRetiro;
    }

    public int getEstacionDevolucion() {
        return estacionDevolucion;
    }

    public void setEstacionDevolucion(int estacionDevolucion) {
        this.estacionDevolucion = estacionDevolucion;
    }

    public LocalDateTime getFechaHoraRetiro() {
        return fechaHoraRetiro;
    }

    public void setFechaHoraRetiro(LocalDateTime fechaHoraRetiro) {
        this.fechaHoraRetiro = fechaHoraRetiro;
    }

    public LocalDateTime getFechaHoraDevolucion() {
        return fechaHoraDevolucion;
    }

    public void setFechaHoraDevolucion(LocalDateTime fechaHoraDevolucion) {
        this.fechaHoraDevolucion = fechaHoraDevolucion;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public int getTarifa() {
        return tarifa;
    }

    public void setTarifa(int tarifa) {
        this.tarifa = tarifa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlquilerEntity that = (AlquilerEntity) o;
        return estado == that.estado && Float.compare(monto, that.monto) == 0 && tarifa == that.tarifa && Objects.equals(estacionRetiro, that.estacionRetiro) && Objects.equals(estacionDevolucion, that.estacionDevolucion) && Objects.equals(fechaHoraRetiro, that.fechaHoraRetiro) && Objects.equals(fechaHoraDevolucion, that.fechaHoraDevolucion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(estado, estacionRetiro, estacionDevolucion, fechaHoraRetiro, fechaHoraDevolucion, monto, tarifa);
    }

    @Override
    public String toString() {
        return "AlquilerEntity{" +
                "estado=" + estado +
                ", estacionRetiro=" + estacionRetiro +
                ", estacionDevolucion=" + estacionDevolucion +
                ", fechaHoraRetiro=" + fechaHoraRetiro +
                ", fechaHoraDevolucion=" + fechaHoraDevolucion +
                ", monto=" + monto +
                ", tarifa=" + tarifa +
                '}';
    }
}
