package com.grupo95.estaciones.repository;

import com.grupo95.estaciones.entity.EstacionEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface EstacionRepository extends JpaRepository<EstacionEntity, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ESTACIONES (fecha_hora_creacion, latitud, longitud, nombre) VALUES (:fechaHoraCreacion, :latitud, :longitud, :nombre)", nativeQuery = true)
    void insertarEstacion(@Param("fechaHoraCreacion") LocalDateTime fechaHoraCreacion, @Param("latitud") Float latitud, @Param("longitud") Float longitud, @Param("nombre") String nombre);
}