package com.bancolombia.aplicacionbancaria.repository;

import com.bancolombia.aplicacionbancaria.model.CuentaEntity;
import com.bancolombia.aplicacionbancaria.model.TransaccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TransaccionRepository extends JpaRepository<TransaccionEntity, Long> {
}
