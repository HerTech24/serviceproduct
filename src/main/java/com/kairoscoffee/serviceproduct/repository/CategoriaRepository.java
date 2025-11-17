package com.kairoscoffee.serviceproduct.repository;

import com.kairoscoffee.serviceproduct.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
