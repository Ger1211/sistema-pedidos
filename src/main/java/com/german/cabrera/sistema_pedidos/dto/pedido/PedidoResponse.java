package com.german.cabrera.sistema_pedidos.dto.pedido;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PedidoResponse {
    private Long id;
    private Long clienteId;
    private List<DetallePedidoResponse> detalles;
    private BigDecimal total;

    // Constructor
    public PedidoResponse(Long id, Long clienteId, List<DetallePedidoResponse> detalles, BigDecimal total) {
        this.id = id;
        this.clienteId = clienteId;
        this.detalles = detalles;
        this.total = total;
    }
}
