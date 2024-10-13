package com.bancolombia.aplicacionbancaria.repository;

import com.bancolombia.aplicacionbancaria.model.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<CuentaEntity, Long> {
}
