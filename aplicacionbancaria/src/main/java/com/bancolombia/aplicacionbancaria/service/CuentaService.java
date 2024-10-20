package com.bancolombia.aplicacionbancaria.service;

import com.bancolombia.aplicacionbancaria.model.CuentaBasica;
import com.bancolombia.aplicacionbancaria.model.CuentaEntity;
import com.bancolombia.aplicacionbancaria.model.CuentaPremium;
import com.bancolombia.aplicacionbancaria.model.dto.CuentaDTO;
import com.bancolombia.aplicacionbancaria.repository.CuentaRepository;
import com.bancolombia.aplicacionbancaria.repository.TransaccionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CuentaService {

    private CuentaRepository cuentaRepository;
    private TransaccionRepository transaccionRepository;

    public CuentaService(CuentaRepository cuentaRepository, TransaccionRepository transaccionRepository) {
        this.cuentaRepository = cuentaRepository;
        this.transaccionRepository = transaccionRepository;
    }

    public String obtenerSaldo(CuentaDTO cuentaDTO) {
        Optional<CuentaEntity> cuentaEncontrada = cuentaRepository.findByNumeroCuenta(cuentaDTO.getCuenta());
        if (cuentaEncontrada.isPresent()) {
            CuentaEntity cuenta = cuentaEncontrada.get();
            if (cuenta instanceof CuentaBasica) {
                return "El saldo de la cuenta básica es: " + ((CuentaBasica) cuenta).getSaldo();
            }
            if (cuenta instanceof CuentaPremium) {
                return "El saldo de la cuenta premium es: " + ((CuentaPremium) cuenta).getSaldo();
            }
        }
        throw new NullPointerException("La cuenta no existe");
    }

    public String obtenerHistorialTransacciones(CuentaDTO cuentaDTO) {
        Optional<CuentaEntity> cuentaEncontrada = cuentaRepository.findByNumeroCuenta(cuentaDTO.getCuenta());
        if (cuentaEncontrada.isPresent()) {
            CuentaEntity cuenta = cuentaEncontrada.get();
            if (cuenta instanceof CuentaBasica) {
                return "Historial de las ultimas 5 transacciones de la cuenta básica : " + ((CuentaBasica) cuenta).getTransacciones().toString();
            }
            if (cuenta instanceof CuentaPremium) {
                return "Historial de las ultimas 5 transacciones de la cuenta premium : " + ((CuentaPremium) cuenta).getTransacciones();
            }
        }
        throw new NullPointerException("La cuenta no existe");
    }

}
