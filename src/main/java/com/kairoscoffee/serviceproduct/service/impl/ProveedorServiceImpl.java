package com.kairoscoffee.serviceproduct.service.impl;

import com.kairoscoffee.serviceproduct.dto.ProveedorDTO;
import com.kairoscoffee.serviceproduct.mapper.ProveedorMapper;
import com.kairoscoffee.serviceproduct.repository.ProveedorRepository;
import com.kairoscoffee.serviceproduct.service.ProveedorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorServiceImpl implements ProveedorService {

    private final ProveedorRepository proveedorRepository;

    public ProveedorServiceImpl(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @Override
    public List<ProveedorDTO> findAll() {
        return proveedorRepository.findAll()
                .stream()
                .map(ProveedorMapper::toDTO)
                .toList();
    }
}
