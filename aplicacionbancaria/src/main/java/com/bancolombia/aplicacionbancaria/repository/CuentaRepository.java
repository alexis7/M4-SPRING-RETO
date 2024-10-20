package com.bancolombia.aplicacionbancaria.repository;

import com.bancolombia.aplicacionbancaria.model.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CuentaRepository extends JpaRepository<CuentaEntity, Long> {

    @Query(value = "SELECT * " +
            "FROM public.cuenta  " +
            "where cuenta.numero_cuenta = :numeroCuenta ", nativeQuery = true)
    Optional<CuentaEntity> findByNumeroCuenta(
            @Param("numeroCuenta") Long numeroCuenta);
}
