package com.german.cabrera.sistema_pedidos.dto.pedido;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PedidoRequest {

    @NotNull
    private Long clienteId;

    @NotNull
    @Size(min = 1)
    private List<DetallePedidoRequest> detalles;

    public PedidoRequest(Long clienteId, List<DetallePedidoRequest> detalles) {
        this.clienteId = clienteId;
        this.detalles = detalles;
    }
}
