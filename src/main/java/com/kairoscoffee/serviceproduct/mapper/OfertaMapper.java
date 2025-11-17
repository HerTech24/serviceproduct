package com.kairoscoffee.serviceproduct.mapper;

import com.kairoscoffee.serviceproduct.dto.OfertaDTO;
import com.kairoscoffee.serviceproduct.entity.Oferta;

public class OfertaMapper {

    public static OfertaDTO toDTO(Oferta o) {
        if (o == null) return null;

        return OfertaDTO.builder()
                .id(o.getId())
                .productoId(o.getProducto() != null ? o.getProducto().getId() : null)
                .descuentoPorc(o.getDescuentoPorc())
                .fechaInicio(o.getFechaInicio() != null ? o.getFechaInicio().toString() : null)
                .fechaFin(o.getFechaFin() != null ? o.getFechaFin().toString() : null)
                .activa("S".equalsIgnoreCase(o.getActiva()))
                .build();
    }
}
