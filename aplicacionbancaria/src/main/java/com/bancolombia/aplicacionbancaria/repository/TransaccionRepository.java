package com.bancolombia.aplicacionbancaria.repository;

import com.bancolombia.aplicacionbancaria.model.TransaccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionRepository extends JpaRepository<TransaccionEntity, Long> {
}
