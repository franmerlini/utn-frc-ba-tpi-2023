package com.grupo95.alquileres.repository;

import com.grupo95.alquileres.entity.TarifaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarifaRepository extends CrudRepository<TarifaEntity, Integer> {

}
