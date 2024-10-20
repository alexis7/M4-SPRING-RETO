package com.bancolombia.aplicacionbancaria.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "transacciones")
public class TransaccionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoTransaccion;

    private BigDecimal monto;

    private Timestamp fecha;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private CuentaEntity cuenta;

    public void setId(Long id) {
        this.id = id;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    public void setCuenta(CuentaEntity cuenta) {
        this.cuenta = cuenta;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public CuentaEntity getCuenta() {
        return cuenta;
    }

    public String toString() {
        return "Transaccion {id=" + id + ", tipo=" + tipoTransaccion + ", monto='" + monto + "', fecha='" + fecha + "'}";
    }

}
