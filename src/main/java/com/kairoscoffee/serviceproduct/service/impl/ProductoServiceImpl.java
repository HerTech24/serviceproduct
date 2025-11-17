package com.kairoscoffee.serviceproduct.service.impl;

import com.kairoscoffee.serviceproduct.dto.ProductoDTO;
import com.kairoscoffee.serviceproduct.entity.Categoria;
import com.kairoscoffee.serviceproduct.entity.Oferta;
import com.kairoscoffee.serviceproduct.entity.Producto;
import com.kairoscoffee.serviceproduct.entity.Proveedor;
import com.kairoscoffee.serviceproduct.mapper.ProductoMapper;
import com.kairoscoffee.serviceproduct.repository.CategoriaRepository;
import com.kairoscoffee.serviceproduct.repository.OfertaRepository;
import com.kairoscoffee.serviceproduct.repository.ProductoRepository;
import com.kairoscoffee.serviceproduct.repository.ProveedorRepository;
import com.kairoscoffee.serviceproduct.service.ProductoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProveedorRepository proveedorRepository;
    private final OfertaRepository ofertaRepository;

    public ProductoServiceImpl(
            ProductoRepository productoRepository,
            CategoriaRepository categoriaRepository,
            ProveedorRepository proveedorRepository,
            OfertaRepository ofertaRepository
    ) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.proveedorRepository = proveedorRepository;
        this.ofertaRepository = ofertaRepository;
    }

    @Override
    public List<ProductoDTO> findAll() {
        return productoRepository.findAll()
                .stream()
                .map(ProductoMapper::toDTO)
                .toList();
    }

    @Override
    public ProductoDTO findById(Long id) {
        return productoRepository.findById(id)
                .map(ProductoMapper::toDTO)
                .orElse(null);
    }

    @Override
    public ProductoDTO save(ProductoDTO dto) {
        Producto producto = mapDTOtoEntity(dto);
        Producto saved = productoRepository.save(producto);
        return ProductoMapper.toDTO(saved);
    }

    @Override
    public ProductoDTO update(Long id, ProductoDTO dto) {
        return productoRepository.findById(id)
                .map(p -> {
                    p.setNombre(dto.getNombre());
                    p.setDescripcion(dto.getDescripcion());
                    p.setPrecio(dto.getPrecio());
                    p.setStock(dto.getStock());

                    if (dto.getCategoriaId() != null) {
                        Categoria cat = categoriaRepository.findById(dto.getCategoriaId()).orElse(null);
                        p.setCategoria(cat);
                    }

                    if (dto.getProveedorId() != null) {
                        Proveedor prov = proveedorRepository.findById(dto.getProveedorId()).orElse(null);
                        p.setProveedor(prov);
                    }

                    if (dto.getOfertaId() != null) {
                        Oferta of = ofertaRepository.findById(dto.getOfertaId()).orElse(null);
                        p.setOferta(of);
                    }

                    return ProductoMapper.toDTO(productoRepository.save(p));
                })
                .orElse(null);
    }

    @Override
    public void delete(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public List<ProductoDTO> findByCategoria(Long categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId)
                .stream()
                .map(ProductoMapper::toDTO)
                .toList();
    }

    @Override
    public List<ProductoDTO> findWithActiveOffers() {
        return productoRepository.findByOferta_ActivaTrue()
                .stream()
                .map(ProductoMapper::toDTO)
                .toList();
    }

    @Override
    public List<ProductoDTO> findByProveedor(Long proveedorId) {
        return productoRepository.findByProveedorId(proveedorId)
                .stream()
                .map(ProductoMapper::toDTO)
                .toList();
    }

    private Producto mapDTOtoEntity(ProductoDTO dto) {
        Producto p = new Producto();
        p.setNombre(dto.getNombre());
        p.setDescripcion(dto.getDescripcion());
        p.setPrecio(dto.getPrecio());
        p.setStock(dto.getStock());

        if (dto.getCategoriaId() != null) {
            p.setCategoria(categoriaRepository.findById(dto.getCategoriaId()).orElse(null));
        }

        if (dto.getProveedorId() != null) {
            p.setProveedor(proveedorRepository.findById(dto.getProveedorId()).orElse(null));
        }

        if (dto.getOfertaId() != null) {
            p.setOferta(ofertaRepository.findById(dto.getOfertaId()).orElse(null));
        }

        return p;
    }
}
