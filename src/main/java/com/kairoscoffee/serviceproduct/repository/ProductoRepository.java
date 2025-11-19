package com.kairoscoffee.serviceproduct.repository;

import com.kairoscoffee.serviceproduct.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByCategoriaId(Long categoriaId);

    List<Producto> findByProveedorId(Long proveedorId);

    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    @Query("SELECT p FROM Producto p WHERE p.stock > 0")
    List<Producto> findProductosConStock();

    @Query(value = """
        SELECT DISTINCT p.* 
        FROM PRODUCTO p
        INNER JOIN OFERTA o ON p.ID_PRODUCTO = o.ID_PRODUCTO
        WHERE o.ACTIVA = 'S' 
        AND o.FECHA_INICIO <= SYSDATE 
        AND o.FECHA_FIN >= SYSDATE
        """, nativeQuery = true)
    List<Producto> findProductosConOfertasActivas();
}