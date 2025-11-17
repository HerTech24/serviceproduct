package com.kairoscoffee.serviceproduct.service;

import com.kairoscoffee.serviceproduct.dto.OfertaDTO;

import java.util.List;

public interface OfertaService {

    List<OfertaDTO> findActiveOffers();
}
