package com.grupo95.estaciones.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getFechaHoraCreacion() {
        return fechaHoraCreacion;
    }

    public void setFechaHoraCreacion(LocalDateTime fechaHoraCreacion) {
        this.fechaHoraCreacion = fechaHoraCreacion;
    }

    public Float getLatitud() {
        return latitud;
    }

    public void setLatitud(Float latitud) {
        this.latitud = latitud;
    }

    public Float getLongitud() {
        return longitud;
    }

    public void setLongitud(Float longitud) {
        this.longitud = longitud;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EstacionEntity that = (EstacionEntity) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(nombre, that.nombre)) return false;
        if (!Objects.equals(fechaHoraCreacion, that.fechaHoraCreacion))
            return false;
        if (!Objects.equals(latitud, that.latitud)) return false;
        return Objects.equals(longitud, that.longitud);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (fechaHoraCreacion != null ? fechaHoraCreacion.hashCode() : 0);
        result = 31 * result + (latitud != null ? latitud.hashCode() : 0);
        result = 31 * result + (longitud != null ? longitud.hashCode() : 0);
        return result;
    }
}
