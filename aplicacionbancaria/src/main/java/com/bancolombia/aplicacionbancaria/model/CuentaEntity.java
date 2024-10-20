package com.bancolombia.aplicacionbancaria.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoCuenta")
@Table(name = "cuenta")
public abstract class CuentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal saldo;

    private String titular;

    private String tipo;

    private Long numeroCuenta;

    @OneToMany(mappedBy = "cuenta")
    private List<TransaccionEntity> transacciones;


    public CuentaEntity(BigDecimal saldo, String titular, String tipo) {
        this.saldo = saldo;
        this.titular = titular;
        this.tipo = tipo;
    }

    public CuentaEntity() {
    }


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
        int totalElementos = transacciones.size();
        transacciones.sort(Comparator.comparing(TransaccionEntity::getFecha));
        int inicio = Math.max(totalElementos - 5, 0);
        List<TransaccionEntity> historialList = new ArrayList<>();
        for (int i = inicio; i < totalElementos; i++) {
            historialList.add(transacciones.get(i));
        }
        return historialList;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public static class CuentaPremiumEntity {
    }

    public static class CuentaBasicaEntity {
    }

    public abstract void depositoDesdeCajeroAutomatico(BigDecimal cantidad);

    public abstract void depositoDesdeSucursal(BigDecimal cantidad);

    public abstract void depositoDesdeOtraCuenta(BigDecimal cantidad);

    public abstract void compraEnEstablecimientoFisico(BigDecimal cantidad);

    public abstract void compraEnPaginaWeb(BigDecimal cantidad);

    public abstract void retiroEnCajero(BigDecimal cantidad);
}
