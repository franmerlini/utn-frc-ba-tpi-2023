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

    @Query(value = "SELECT MAX(a.id) FROM ALQUILERES a", nativeQuery = true)
    int findLastInsertedId();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ALQUILERES (ID_CLIENTE,ESTADO, ESTACION_RETIRO, FECHA_HORA_RETIRO) VALUES (:idCliente,:estado, :estacionRetiro, :fechaHoraRetiro)", nativeQuery = true)
    void insertarAlquiler(
            @Param("idCliente") int idCliente,
            @Param("estado") int estado,
            @Param("estacionRetiro") int estacionRetiro,
            @Param("fechaHoraRetiro") LocalDateTime fechaHoraRetiro
    );

    @Modifying
    @Transactional
    @Query(value = "UPDATE ALQUILERES SET ESTADO = :estado, FECHA_HORA_DEVOLICION= :fechaHoraDevolucion WHERE ID = :id", nativeQuery = true)
    void finalizarAlquiler(@Param("id") int id, @Param("estado") int estado, @Param("fechaHoraDevolucion") LocalDateTime fechaHoraDevolucion);


    @Query(value = "SELECT * FROM ALQUILERES WHERE ID = :id", nativeQuery = true)
    AlquilerEntity obtenerDetallesAlquiler(@Param("id") int id);



}