package com.bancolombia.aplicacionbancaria.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Validated
public class TransaccionDTO {

    @NotNull(message = "La cuenta no puede ser nula")
    private Long cuenta;

    @NotNull(message = "Debe enviar el tipo de transaccion")
    @NotEmpty(message = "El tipo de transaccion no puede ser vacio")
    private String tipoTransaccion;

    @NotNull(message = "El monto no puede ser nulo")
    @Positive(message = "El monto debe ser mayor a cero")
    private BigDecimal monto;

    private Timestamp fechaTransaccion;

    public TransaccionDTO(BigDecimal monto, String tipoTransaccion, Long cuenta, Timestamp fechaTransaccion) {
        this.cuenta =  cuenta;
        this.tipoTransaccion = tipoTransaccion;
        this.monto = monto;
        this.fechaTransaccion = fechaTransaccion;

    }

    public BigDecimal getMonto() {
        return monto;
    }

    public String getDescripcion() {
        return tipoTransaccion;
    }

    public Long getCuenta() {
        return cuenta;
    }

    public Timestamp getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(Timestamp fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setCuenta(Long cuenta) {
        this.cuenta = cuenta;
    }

}
