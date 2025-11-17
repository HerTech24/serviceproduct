package com.kairoscoffee.serviceproduct.controller;

import com.kairoscoffee.serviceproduct.dto.ProductoDTO;
import com.kairoscoffee.serviceproduct.service.ProductoService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // ==========================================
    //              ENDPOINTS PUBLICOS
    // ==========================================

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listar() {
        return ResponseEntity.ok(productoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtener(@PathVariable Long id) {
        ProductoDTO dto = productoService.findById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<ProductoDTO>> listarPorCategoria(@PathVariable Long idCategoria) {
        return ResponseEntity.ok(productoService.findByCategoria(idCategoria));
    }

    @GetMapping("/ofertas/activos")
    public ResponseEntity<List<ProductoDTO>> listarConOfertas() {
        return ResponseEntity.ok(productoService.findWithActiveOffers());
    }

    @GetMapping("/proveedor/{idProveedor}")
    public ResponseEntity<List<ProductoDTO>> listarPorProveedor(@PathVariable Long idProveedor) {
        return ResponseEntity.ok(productoService.findByProveedor(idProveedor));
    }

    // ==========================================
    //            ENDPOINTS SOLO ADMIN
    // ==========================================

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping
    public ResponseEntity<ProductoDTO> crear(@RequestBody ProductoDTO dto) {
        return ResponseEntity.ok(productoService.save(dto));
    }

    @PreAuthorize("hasAuthority('admin')")
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizar(@PathVariable Long id, @RequestBody ProductoDTO dto) {
        ProductoDTO actualizado = productoService.update(id, dto);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
