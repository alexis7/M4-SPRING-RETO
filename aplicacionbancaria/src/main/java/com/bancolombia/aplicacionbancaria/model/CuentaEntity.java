package com.bancolombia.aplicacionbancaria.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "cuenta")
public class CuentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal saldo;

    private String titular;

    public CuentaEntity(BigDecimal saldo, String titular) {
        this.saldo = saldo;
        this.titular = titular;
    }

    public CuentaEntity() {
    }

    @OneToMany(mappedBy = "cuenta")
    private List<TransaccionEntity> transacciones;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public String getTitular() {
        return titular;
    }

    public List<TransaccionEntity> getTransacciones() {
        return transacciones;
    }


}
