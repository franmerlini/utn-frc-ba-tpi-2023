package com.grupo95.alquileres.repository;

import com.grupo95.alquileres.entity.AlquilerEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AlquilerRepository extends CrudRepository<AlquilerEntity, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ALQUILERES (ESTADO, ESTACION_RETIRO, ESTACION_DEVOLUCION, FECHA_HORA_RETIRO, FECHA_HORA_DEVOLUCION, MONTO, ID_TARIFA) VALUES (:estado, :estacionRetiro, :estacionDevolucion, :fechaHoraRetiro, :fechaHoraDevolucion, :monto, :tarifa)", nativeQuery = true)
    void insertarAlquiler(
            @Param("estado") int estado,
            @Param("estacionRetiro") int estacionRetiro,
            @Param("estacionDevolucion") int estacionDevolucion,
            @Param("fechaHoraRetiro") LocalDateTime fechaHoraRetiro,
            @Param("fechaHoraDevolucion") LocalDateTime fechaHoraDevolucion,
            @Param("monto") float monto,
            @Param("tarifa") int tarifa
    );

    @Modifying
    @Transactional
    @Query(value = "UPDATE ALQUILERES SET ESTADO = :estado, FECHA_HORA_DEVOLICION= :fechaHoraDevolucion WHERE ID = :id", nativeQuery = true)
    void finalizarAlquiler(@Param("id") int id, @Param("estado") int estado, @Param("fechaHoraDevolucion") LocalDateTime fechaHoraDevolucion);


    @Query(value = "SELECT * FROM ALQUILERES WHERE ID = :id", nativeQuery = true)
    AlquilerEntity obtenerDetallesAlquiler(@Param("id") int id);



}