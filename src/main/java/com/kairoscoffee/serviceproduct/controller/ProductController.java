package com.kairoscoffee.serviceproduct.controller;

import com.kairoscoffee.serviceproduct.dto.ProductoDTO;
import com.kairoscoffee.serviceproduct.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductoService productoService;

    @GetMapping("/public/info")
    public ResponseEntity<?> publicInfo() {
        return ResponseEntity.ok("KairosCoffee Product Service");
    }

    @GetMapping("/user/list")
    public ResponseEntity<List<ProductoDTO>> listProducts() {
        return ResponseEntity.ok(productoService.findAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ProductoDTO> getById(@PathVariable Long id) {
        ProductoDTO dto = productoService.findById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/user/categoria/{categoriaId}")
    public ResponseEntity<List<ProductoDTO>> filterByCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(productoService.findByCategoria(categoriaId));
    }

    @GetMapping("/user/proveedor/{proveedorId}")
    public ResponseEntity<List<ProductoDTO>> filterByProveedor(@PathVariable Long proveedorId) {
        return ResponseEntity.ok(productoService.findByProveedor(proveedorId));
    }

    @GetMapping("/user/buscar")
    public ResponseEntity<List<ProductoDTO>> searchByName(@RequestParam String nombre) {
        return ResponseEntity.ok(productoService.findByNombre(nombre));
    }

    @GetMapping("/user/stock")
    public ResponseEntity<List<ProductoDTO>> getProductsWithStock() {
        return ResponseEntity.ok(productoService.findProductosConStock());
    }

    @GetMapping("/user/ofertas")
    public ResponseEntity<List<ProductoDTO>> getProductsWithOffers() {
        return ResponseEntity.ok(productoService.findProductosConOfertasActivas());
    }

    @PostMapping("/admin")
    public ResponseEntity<ProductoDTO> create(@RequestBody ProductoDTO dto) {
        ProductoDTO created = productoService.save(dto);
        return ResponseEntity
                .created(URI.create("/product/admin/" + created.getId()))
                .body(created);
    }

    @PutMapping("/admin/{id}")
    public ResponseEntity<ProductoDTO> update(@PathVariable Long id, @RequestBody ProductoDTO dto) {
        ProductoDTO updated = productoService.update(id, dto);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/admin/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ProductoDTO dto = productoService.findById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/admin/dashboard")
    public ResponseEntity<?> adminDashboard() {
        return ResponseEntity.ok("Admin Dashboard - KairosCoffee");
    }
}