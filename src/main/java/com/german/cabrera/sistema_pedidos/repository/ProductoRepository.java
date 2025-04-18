package com.german.cabrera.sistema_pedidos.repository;

import com.german.cabrera.sistema_pedidos.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
