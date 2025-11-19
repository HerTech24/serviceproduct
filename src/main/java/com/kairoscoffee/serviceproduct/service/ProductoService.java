package com.kairoscoffee.serviceproduct.service;

import com.kairoscoffee.serviceproduct.dto.ProductoDTO;
import java.util.List;

public interface ProductoService {

    List<ProductoDTO> findAll();
    ProductoDTO findById(Long id);
    ProductoDTO save(ProductoDTO dto);
    ProductoDTO update(Long id, ProductoDTO dto);
    void delete(Long id);

    List<ProductoDTO> findByCategoria(Long categoriaId);
    List<ProductoDTO> findByProveedor(Long proveedorId);
    List<ProductoDTO> findByNombre(String nombre);
    List<ProductoDTO> findProductosConStock();
    List<ProductoDTO> findProductosConOfertasActivas();
}