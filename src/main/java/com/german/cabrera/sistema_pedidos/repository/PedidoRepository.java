package com.german.cabrera.sistema_pedidos.repository;


import com.german.cabrera.sistema_pedidos.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
