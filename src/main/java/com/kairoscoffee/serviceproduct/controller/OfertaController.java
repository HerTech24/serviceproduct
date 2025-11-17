package com.kairoscoffee.serviceproduct.controller;

import com.kairoscoffee.serviceproduct.dto.OfertaDTO;
import com.kairoscoffee.serviceproduct.service.OfertaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ofertas")
@CrossOrigin(origins = "*")
public class OfertaController {

    private final OfertaService ofertaService;

    public OfertaController(OfertaService ofertaService) {
        this.ofertaService = ofertaService;
    }

    @GetMapping("/activas")
    public ResponseEntity<List<OfertaDTO>> listarActivas() {
        return ResponseEntity.ok(ofertaService.findActiveOffers());
    }
}
