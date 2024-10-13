package com.bancolombia.aplicacionbancaria.service;

import com.bancolombia.aplicacionbancaria.model.CuentaEntity;
import com.bancolombia.aplicacionbancaria.model.TransaccionEntity;
import com.bancolombia.aplicacionbancaria.model.dto.CuentaDTO;
import com.bancolombia.aplicacionbancaria.model.dto.TransaccionDTO;
import com.bancolombia.aplicacionbancaria.repository.CuentaRepository;
import com.bancolombia.aplicacionbancaria.repository.TransaccionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
       Optional<CuentaEntity> cuentaEncontrada = cuentaRepository.findById(cuentaDTO.getCuenta());
        if(!cuentaEncontrada.isPresent()){
            throw new NullPointerException("La cuenta no existe");
        }
        return "El saldo de la cuenta es: " + cuentaEncontrada.get().getSaldo();
    }

    public String depositar(TransaccionDTO transaccionDTO) {
        Optional<CuentaEntity> cuentaEncontrada = cuentaRepository.findById(transaccionDTO.getCuenta());
        if(!cuentaEncontrada.isPresent()){
            throw new NullPointerException("La cuenta no existe");
        }
        if(transaccionDTO.getMonto().compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("El monto a depositar debe ser mayor a cero");
        }else{
            TransaccionEntity transaccion = new TransaccionEntity();
            transaccion.setCuenta(cuentaEncontrada.get());
            transaccion.setTipoTransaccion(transaccionDTO.getDescripcion());
            transaccion.setMonto(transaccionDTO.getMonto());
            transaccion.setFecha(new java.sql.Timestamp(System.currentTimeMillis()));
            transaccionRepository.save(transaccion);
            CuentaEntity cuenta = cuentaEncontrada.get();
            cuenta.setSaldo(cuenta.getSaldo().add(transaccionDTO.getMonto()));
            cuentaRepository.save(cuenta);
        }
        return "El saldo luego del deposito es: " + cuentaEncontrada.get().getSaldo();
    }

    public String retirar(TransaccionDTO transaccionDTO) {
        Optional<CuentaEntity> cuentaEncontrada = cuentaRepository.findById(transaccionDTO.getCuenta());
        if(!cuentaEncontrada.isPresent()){
            throw new NullPointerException("La cuenta no existe");
        }
        if(transaccionDTO.getMonto().compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("El monto a retirar debe ser mayor a cero");
        }
        if(cuentaEncontrada.get().getSaldo().compareTo(transaccionDTO.getMonto()) < 0){
            throw new IllegalStateException("Saldo insuficiente para realizar esta transacción.");
        }
        TransaccionEntity transaccion = new TransaccionEntity();
        transaccion.setCuenta(cuentaEncontrada.get());
        transaccion.setTipoTransaccion(transaccionDTO.getDescripcion());
        transaccion.setMonto(transaccionDTO.getMonto());
        transaccion.setFecha(new java.sql.Timestamp(System.currentTimeMillis()));
        transaccionRepository.save(transaccion);
        CuentaEntity cuenta = cuentaEncontrada.get();
        cuenta.setSaldo(cuenta.getSaldo().subtract(transaccionDTO.getMonto()));
        cuentaRepository.save(cuenta);
        return "El saldo luego del retiro es : " + cuentaEncontrada.get().getSaldo();
    }
}
