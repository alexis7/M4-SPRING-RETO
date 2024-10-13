package com.bancolombia.aplicacionbancaria.controller;

import com.bancolombia.aplicacionbancaria.model.dto.CuentaDTO;
import com.bancolombia.aplicacionbancaria.model.dto.TransaccionDTO;
import com.bancolombia.aplicacionbancaria.service.CuentaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuenta")
public class CuentaController {

    private CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @PostMapping("/saldo")
    public String obtenerSaldo(@Valid @RequestBody CuentaDTO cuenta) {
        return cuentaService.obtenerSaldo(cuenta);
    }

    @PostMapping("/deposito")
    public String depositar(@Valid @RequestBody TransaccionDTO transaccionDTO) {
        return cuentaService.depositar(transaccionDTO);
    }

    @PostMapping("/retiro")
    public String retirar(@Valid @RequestBody TransaccionDTO transaccionDTO) {
        return cuentaService.retirar(transaccionDTO);
    }

}
