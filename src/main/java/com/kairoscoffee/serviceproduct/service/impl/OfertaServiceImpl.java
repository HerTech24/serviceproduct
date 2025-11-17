package com.kairoscoffee.serviceproduct.service.impl;

import com.kairoscoffee.serviceproduct.dto.OfertaDTO;
import com.kairoscoffee.serviceproduct.mapper.OfertaMapper;
import com.kairoscoffee.serviceproduct.repository.OfertaRepository;
import com.kairoscoffee.serviceproduct.service.OfertaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfertaServiceImpl implements OfertaService {

    private final OfertaRepository ofertaRepository;

    public OfertaServiceImpl(OfertaRepository ofertaRepository) {
        this.ofertaRepository = ofertaRepository;
    }

    @Override
    public List<OfertaDTO> findActiveOffers() {
        return ofertaRepository.findByActivaTrue()
                .stream()
                .map(OfertaMapper::toDTO)
                .toList();
    }
}
