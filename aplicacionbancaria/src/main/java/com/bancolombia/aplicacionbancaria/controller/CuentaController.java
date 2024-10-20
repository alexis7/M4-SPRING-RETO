package com.bancolombia.aplicacionbancaria.controller;

import com.bancolombia.aplicacionbancaria.model.dto.CuentaDTO;
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

    @GetMapping("/saldo")
    public String obtenerSaldo(@Valid @RequestBody CuentaDTO cuenta) {
        return cuentaService.obtenerSaldo(cuenta);
    }

    @GetMapping("/historial")
    public String obteneHistorialrSaldo(@Valid @RequestBody CuentaDTO cuenta) {
        return cuentaService.obtenerHistorialTransacciones(cuenta);
    }

}
