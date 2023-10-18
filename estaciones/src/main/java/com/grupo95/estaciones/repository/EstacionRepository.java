package com.grupo95.estaciones.repository;

import com.grupo95.estaciones.entity.EstacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstacionRepository extends JpaRepository<EstacionEntity, Integer> {
}