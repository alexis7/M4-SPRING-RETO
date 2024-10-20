package com.bancolombia.aplicacionbancaria.controller;


import com.bancolombia.aplicacionbancaria.model.dto.TransaccionDTO;
import com.bancolombia.aplicacionbancaria.service.TransaccionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaccion")
public class TransaccionController {

    private TransaccionService transaccionService;

    public TransaccionController(TransaccionService transaccionService) {
        this.transaccionService = transaccionService;
    }

    @PostMapping("/deposito/sucursal")
    public String depositarSucursal(@Valid @RequestBody TransaccionDTO transaccionDTO) {
        return transaccionService.depositoDesdeSucursal(transaccionDTO);
    }

    @PostMapping("/deposito/cajero")
    public String depositarCajero(@Valid @RequestBody TransaccionDTO transaccionDTO) {
        return transaccionService.depositoDesdeCajero(transaccionDTO);
    }

    @PostMapping("/deposito/otracuenta")
    public String depositarOtraCuenta(@Valid @RequestBody TransaccionDTO transaccionDTO) {
        return transaccionService.depositarDesdeOtraCuenta(transaccionDTO);
    }

    @PostMapping("/compra/fisico")
    public String compraFisica(@Valid @RequestBody TransaccionDTO transaccionDTO) {
        return transaccionService.compraEstablecimientoFisico(transaccionDTO);
    }

    @PostMapping("/compra/web")
    public String compraWeb(@Valid @RequestBody TransaccionDTO transaccionDTO) {
        return transaccionService.compraPaginaWeb(transaccionDTO);
    }

    @PostMapping("/retiro/cajero")
    public String retirar(@Valid @RequestBody TransaccionDTO transaccionDTO) {
        return transaccionService.retirarEnCajero(transaccionDTO);
    }
}
