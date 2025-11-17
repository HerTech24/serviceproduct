package com.kairoscoffee.serviceproduct.mapper;

import com.kairoscoffee.serviceproduct.dto.CategoriaDTO;
import com.kairoscoffee.serviceproduct.entity.Categoria;

public class CategoriaMapper {

    // ---------- ENTITY → DTO ----------
    public static CategoriaDTO toDTO(Categoria categoria) {
        if (categoria == null) return null;

        return CategoriaDTO.builder()
                .id(categoria.getId())
                .nombre(categoria.getNombre())
                .descripcion(categoria.getDescripcion())
                .build();
    }

    // ---------- DTO → ENTITY ----------
    public static Categoria toEntity(CategoriaDTO dto) {
        if (dto == null) return null;

        Categoria categoria = new Categoria();
        categoria.setId(dto.getId()); // opcional, Hibernate lo ignora si es null
        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());
        return categoria;
    }
}
