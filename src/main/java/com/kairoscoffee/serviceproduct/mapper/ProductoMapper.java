package com.kairoscoffee.serviceproduct.mapper;

import com.kairoscoffee.serviceproduct.dto.ProductoDTO;
import com.kairoscoffee.serviceproduct.entity.Producto;

public class ProductoMapper {

    public static ProductoDTO toDTO(Producto p) {
        if (p == null) return null;

        return ProductoDTO.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .descripcion(p.getDescripcion())
                .precio(p.getPrecio())
                .stock(p.getStock())

                .categoriaId(p.getCategoria() != null ? p.getCategoria().getId() : null)
                .categoriaNombre(p.getCategoria() != null ? p.getCategoria().getNombre() : null)

                .proveedorId(p.getProveedor() != null ? p.getProveedor().getId() : null)
                .proveedorNombre(p.getProveedor() != null ? p.getProveedor().getNombre() : null)

                .ofertaId(p.getOferta() != null ? p.getOferta().getId() : null)

                .ofertaDescuento(
                        p.getOferta() != null && p.getOferta().getDescuentoPorc() != null
                                ? p.getOferta().getDescuentoPorc()
                                : null
                )

                .ofertaActiva(
                        p.getOferta() != null && p.getOferta().getActiva() != null
                                ? "S".equalsIgnoreCase(p.getOferta().getActiva())
                                : null
                )

                .build();
    }
}