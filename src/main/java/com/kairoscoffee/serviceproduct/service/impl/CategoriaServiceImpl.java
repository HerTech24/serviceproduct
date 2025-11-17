package com.kairoscoffee.serviceproduct.service.impl;

import com.kairoscoffee.serviceproduct.dto.CategoriaDTO;
import com.kairoscoffee.serviceproduct.entity.Categoria;
import com.kairoscoffee.serviceproduct.mapper.CategoriaMapper;
import com.kairoscoffee.serviceproduct.repository.CategoriaRepository;
import com.kairoscoffee.serviceproduct.service.CategoriaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<CategoriaDTO> findAll() {
        return categoriaRepository.findAll()
                .stream()
                .map(CategoriaMapper::toDTO)
                .toList();
    }

    @Override
    public CategoriaDTO create(CategoriaDTO dto) {
        Categoria entity = CategoriaMapper.toEntity(dto);
        Categoria saved = categoriaRepository.save(entity);
        return CategoriaMapper.toDTO(saved);
    }
}
