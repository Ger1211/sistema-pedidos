package com.german.cabrera.sistema_pedidos.model;

import com.german.cabrera.sistema_pedidos.dto.pedido.DetallePedidoRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDetalle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    public PedidoDetalle(DetallePedidoRequest detallePedidoRequest, Pedido pedido, Producto producto) {
        this.cantidad = detallePedidoRequest.getCantidad();
        this.pedido = pedido;
        this.producto = producto;
    }
}
