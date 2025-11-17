package com.kairoscoffee.serviceproduct.repository;

import com.kairoscoffee.serviceproduct.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByCategoriaId(Long categoriaId);

    List<Producto> findByProveedorId(Long proveedorId);

    List<Producto> findByOferta_ActivaTrue();
}
