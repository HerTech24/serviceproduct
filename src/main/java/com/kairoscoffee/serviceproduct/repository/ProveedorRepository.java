package com.kairoscoffee.serviceproduct.repository;

import com.kairoscoffee.serviceproduct.entity.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {

}
