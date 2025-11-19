package com.kairoscoffee.serviceproduct.service.impl;

import com.kairoscoffee.serviceproduct.dto.ProductoDTO;
import com.kairoscoffee.serviceproduct.entity.Producto;
import com.kairoscoffee.serviceproduct.mapper.ProductoMapper;
import com.kairoscoffee.serviceproduct.repository.ProductoRepository;
import com.kairoscoffee.serviceproduct.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository repository;
    private final ProductoMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoDTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElse(null);
    }

    @Override
    @Transactional
    public ProductoDTO save(ProductoDTO dto) {
        Producto entity = mapper.toEntity(dto);
        Producto saved = repository.save(entity);
        return mapper.toDTO(saved);
    }

    @Override
    @Transactional
    public ProductoDTO update(Long id, ProductoDTO dto) {
        return repository.findById(id)
                .map(producto -> {
                    producto.setNombre(dto.getNombre());
                    producto.setDescripcion(dto.getDescripcion());
                    producto.setPrecio(dto.getPrecio());
                    producto.setStock(dto.getStock());
                    producto.setCategoriaId(dto.getCategoriaId());
                    producto.setProveedorId(dto.getProveedorId());
                    producto.setUrlImagen(dto.getUrlImagen());

                    Producto updated = repository.save(producto);
                    return mapper.toDTO(updated);
                })
                .orElse(null);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO> findByCategoria(Long categoriaId) {
        return repository.findByCategoriaId(categoriaId)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO> findByProveedor(Long proveedorId) {
        return repository.findByProveedorId(proveedorId)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO> findByNombre(String nombre) {
        return repository.findByNombreContainingIgnoreCase(nombre)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO> findProductosConStock() {
        return repository.findProductosConStock()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoDTO> findProductosConOfertasActivas() {
        List<Producto> productosConOfertas = repository.findProductosConOfertasActivas();

        return productosConOfertas.stream()
                .map(producto -> {
                    ProductoDTO dto = mapper.toDTO(producto);
                    dto.setOfertaActiva(true);
                    return dto;
                })
                .toList();
    }
}