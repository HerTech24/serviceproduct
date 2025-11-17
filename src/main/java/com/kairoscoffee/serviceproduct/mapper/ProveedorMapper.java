package com.kairoscoffee.serviceproduct.mapper;

import com.kairoscoffee.serviceproduct.dto.ProveedorDTO;
import com.kairoscoffee.serviceproduct.entity.Proveedor;

public class ProveedorMapper {

    public static ProveedorDTO toDTO(Proveedor proveedor) {
        if (proveedor == null) return null;

        return ProveedorDTO.builder()
                .id(proveedor.getId())
                .nombre(proveedor.getNombre())
                .email(proveedor.getEmail())
                .telefono(proveedor.getTelefono())
                .build();
    }
}
