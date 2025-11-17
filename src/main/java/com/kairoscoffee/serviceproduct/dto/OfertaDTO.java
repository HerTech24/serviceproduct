package com.kairoscoffee.serviceproduct.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfertaDTO {

    private Long id;
    private Long productoId;
    private Double descuentoPorc;
    private String fechaInicio;
    private String fechaFin;
    private Boolean activa;
}
