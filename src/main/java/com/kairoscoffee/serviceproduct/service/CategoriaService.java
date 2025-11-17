package com.kairoscoffee.serviceproduct.service;

import com.kairoscoffee.serviceproduct.dto.CategoriaDTO;

import java.util.List;

public interface CategoriaService {

    List<CategoriaDTO> findAll();
    CategoriaDTO create(CategoriaDTO dto);
}
