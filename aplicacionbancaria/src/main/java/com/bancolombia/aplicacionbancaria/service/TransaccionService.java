package com.bancolombia.aplicacionbancaria.service;

import com.bancolombia.aplicacionbancaria.model.CuentaEntity;
import com.bancolombia.aplicacionbancaria.model.TransaccionEntity;
import com.bancolombia.aplicacionbancaria.model.dto.TransaccionDTO;
import com.bancolombia.aplicacionbancaria.repository.CuentaRepository;
import com.bancolombia.aplicacionbancaria.repository.TransaccionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransaccionService {

    private CuentaRepository cuentaRepository;
    private TransaccionRepository transaccionRepository;

    public TransaccionService(CuentaRepository cuentaRepository, TransaccionRepository transaccionRepository) {
        this.cuentaRepository = cuentaRepository;
        this.transaccionRepository = transaccionRepository;
    }

    public String depositoDesdeSucursal(TransaccionDTO transaccionDTO) {
        Optional<CuentaEntity> cuentaEncontrada = cuentaRepository.findByNumeroCuenta(transaccionDTO.getCuenta());
        validacionesDeposito(transaccionDTO.getMonto(), cuentaEncontrada);
        TransaccionEntity transaccion = cargarTransaccion(transaccionDTO, cuentaEncontrada);
        cuentaEncontrada.get().depositoDesdeSucursal(transaccionDTO.getMonto());
        transaccionRepository.save(transaccion);
        cuentaRepository.save(cuentaEncontrada.get());
        return "El saldo luego del deposito desde sucursal es: " + cuentaEncontrada.get().getSaldo();
    }

    public String depositoDesdeCajero(TransaccionDTO transaccionDTO) {
        Optional<CuentaEntity> cuentaEncontrada = cuentaRepository.findByNumeroCuenta(transaccionDTO.getCuenta());
        validacionesDeposito(transaccionDTO.getMonto(), cuentaEncontrada);
        TransaccionEntity transaccion = cargarTransaccion(transaccionDTO, cuentaEncontrada);
        cuentaEncontrada.get().depositoDesdeCajeroAutomatico(transaccionDTO.getMonto());
        transaccionRepository.save(transaccion);
        cuentaRepository.save(cuentaEncontrada.get());
        return "El saldo luego del deposito desde cajero es: " + cuentaEncontrada.get().getSaldo();
    }

    public String depositarDesdeOtraCuenta(TransaccionDTO transaccionDTO) {
        Optional<CuentaEntity> cuentaEncontrada = cuentaRepository.findByNumeroCuenta(transaccionDTO.getCuenta());
        validacionesDeposito(transaccionDTO.getMonto(), cuentaEncontrada);
        TransaccionEntity transaccion = cargarTransaccion(transaccionDTO, cuentaEncontrada);
        cuentaEncontrada.get().depositoDesdeOtraCuenta(transaccionDTO.getMonto());
        transaccionRepository.save(transaccion);
        cuentaRepository.save(cuentaEncontrada.get());
        return "El saldo luego del deposito desde otra cuenta es: " + cuentaEncontrada.get().getSaldo();
    }

    public String compraEstablecimientoFisico(TransaccionDTO transaccionDTO) {
        Optional<CuentaEntity> cuentaEncontrada = cuentaRepository.findByNumeroCuenta(transaccionDTO.getCuenta());
        validacionesRetiro(transaccionDTO.getMonto(), cuentaEncontrada);
        TransaccionEntity transaccion = cargarTransaccion(transaccionDTO, cuentaEncontrada);
        cuentaEncontrada.get().compraEnEstablecimientoFisico(transaccionDTO.getMonto());
        transaccionRepository.save(transaccion);
        cuentaRepository.save(cuentaEncontrada.get());
        return "El saldo luego de la compra en el establecimiento fisico es: " + cuentaEncontrada.get().getSaldo();
    }

    public String compraPaginaWeb(TransaccionDTO transaccionDTO) {
        Optional<CuentaEntity> cuentaEncontrada = cuentaRepository.findByNumeroCuenta(transaccionDTO.getCuenta());
        validacionesRetiro(transaccionDTO.getMonto(), cuentaEncontrada);
        TransaccionEntity transaccion = cargarTransaccion(transaccionDTO, cuentaEncontrada);
        cuentaEncontrada.get().compraEnPaginaWeb(transaccionDTO.getMonto());
        transaccionRepository.save(transaccion);
        cuentaRepository.save(cuentaEncontrada.get());
        return "El saldo luego de la compra en la pagina web es: " + cuentaEncontrada.get().getSaldo();
    }

    public String retirarEnCajero(TransaccionDTO transaccionDTO) {
        Optional<CuentaEntity> cuentaEncontrada = cuentaRepository.findByNumeroCuenta(transaccionDTO.getCuenta());
        validacionesRetiro(transaccionDTO.getMonto(), cuentaEncontrada);
        TransaccionEntity transaccion = cargarTransaccion(transaccionDTO, cuentaEncontrada);
        cuentaEncontrada.get().retiroEnCajero(transaccionDTO.getMonto());
        transaccionRepository.save(transaccion);
        cuentaRepository.save(cuentaEncontrada.get());
        return "El saldo luego del retiro en cajero es: " + cuentaEncontrada.get().getSaldo();
    }

    public TransaccionEntity cargarTransaccion(TransaccionDTO transaccionDTO, Optional<CuentaEntity> cuentaEncontrada) {
        TransaccionEntity transaccion = new TransaccionEntity();
        transaccion.setCuenta(cuentaEncontrada.get());
        transaccion.setTipoTransaccion(transaccionDTO.getDescripcion());
        transaccion.setMonto(transaccionDTO.getMonto());
        transaccion.setFecha(new java.sql.Timestamp(System.currentTimeMillis()));
        return transaccion;
    }

    public void validacionesRetiro(BigDecimal monto, Optional<CuentaEntity> cuentaEncontrada) {
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser mayor a cero");
        }
        if (!cuentaEncontrada.isPresent()) {
            throw new NullPointerException("La cuenta no existe");
        }
        if (cuentaEncontrada.get().getSaldo().compareTo(monto) < 0) {
            throw new IllegalStateException("Saldo insuficiente");
        }
    }

    public void validacionesDeposito(BigDecimal monto, Optional<CuentaEntity> cuentaEncontrada) {
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser mayor a cero");
        }
        if (!cuentaEncontrada.isPresent()) {
            throw new NullPointerException("La cuenta no existe");
        }
    }

}
