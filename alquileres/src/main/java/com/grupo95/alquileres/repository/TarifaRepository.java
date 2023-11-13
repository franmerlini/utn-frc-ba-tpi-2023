package com.grupo95.alquileres.repository;

import com.grupo95.alquileres.entity.TarifaEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarifaRepository extends CrudRepository<TarifaEntity, Integer> {

    @Query(value = "SELECT * FROM TARIFAS t WHERE t.DEFINICION = 'C' AND t.DIA_MES = :diaMes AND t.MES = :mes AND t.ANIO = :anio", nativeQuery = true)
    TarifaEntity findTarifaByDefinicionCAndDiaMesAndAnio(@Param("diaMes") int diaMes, @Param("mes") int mes, @Param("anio") int anio);

    // Buscar tarifas por definicion 'S' y día de la semana actual
    @Query(value = "SELECT * FROM TARIFAS t WHERE t.DEFINICION = 'S' AND t.DIA_SEMANA = :diaSemana", nativeQuery = true)
    TarifaEntity findTarifaByDefinicionSAndDiaSemana(@Param("diaSemana") int diaSemana);

}
