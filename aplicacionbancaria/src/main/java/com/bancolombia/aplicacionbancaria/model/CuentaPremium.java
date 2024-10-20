package com.bancolombia.aplicacionbancaria.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("Premium")
public class CuentaPremium extends CuentaEntity {

    @Override
    public void depositoDesdeCajeroAutomatico(BigDecimal cantidad) {
        BigDecimal saldo = getSaldo();
        saldo = saldo.add(cantidad);
        setSaldo(saldo);
    }

    @Override
    public void depositoDesdeSucursal(BigDecimal cantidad) {
        BigDecimal saldo = getSaldo();
        saldo = saldo.add(cantidad);
        setSaldo(saldo);
    }

    @Override
    public void depositoDesdeOtraCuenta(BigDecimal cantidad) {
        BigDecimal saldo = getSaldo();
        saldo = saldo.add(cantidad.subtract(new BigDecimal(1.5)));
        setSaldo(saldo);
    }

    @Override
    public void compraEnEstablecimientoFisico(BigDecimal cantidad) {
        BigDecimal saldo = getSaldo();
        saldo = saldo.subtract(cantidad);
        setSaldo(saldo);
    }

    @Override
    public void compraEnPaginaWeb(BigDecimal cantidad) {
        BigDecimal saldo = getSaldo();
        saldo = saldo.subtract(cantidad.add(new BigDecimal(5)));
        setSaldo(saldo);
    }

    @Override
    public void retiroEnCajero(BigDecimal cantidad) {
        BigDecimal saldo = getSaldo();
        saldo = saldo.subtract(cantidad);
        setSaldo(saldo);
    }
}
