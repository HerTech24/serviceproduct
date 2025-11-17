package com.kairoscoffee.serviceproduct.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductoDTO {

    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;
    private Integer stock;

    private Long categoriaId;
    private String categoriaNombre;

    private Long proveedorId;
    private String proveedorNombre;

    private Long ofertaId;
    private Double ofertaDescuento;
    private Boolean ofertaActiva;
}
