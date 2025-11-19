package com.kairoscoffee.serviceproduct.mapper;

import com.kairoscoffee.serviceproduct.dto.ProductoDTO;
import com.kairoscoffee.serviceproduct.entity.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {

    public ProductoDTO toDTO(Producto p) {
        return ProductoDTO.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .descripcion(p.getDescripcion())
                .precio(p.getPrecio())
                .stock(p.getStock())
                .categoriaId(p.getCategoriaId())
                .proveedorId(p.getProveedorId())
                .urlImagen(p.getUrlImagen())
                .ofertaActiva(false)
                .build();
    }

    public Producto toEntity(ProductoDTO dto) {
        return Producto.builder()
                .id(dto.getId())
                .nombre(dto.getNombre())
                .descripcion(dto.getDescripcion())
                .precio(dto.getPrecio())
                .stock(dto.getStock())
                .categoriaId(dto.getCategoriaId())
                .proveedorId(dto.getProveedorId())
                .urlImagen(dto.getUrlImagen())
                .build();
    }
}