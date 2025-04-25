package com.german.cabrera.sistema_pedidos.repository;


import com.german.cabrera.sistema_pedidos.model.Pedido;
import com.german.cabrera.sistema_pedidos.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByCliente(Usuario cliente);
}
