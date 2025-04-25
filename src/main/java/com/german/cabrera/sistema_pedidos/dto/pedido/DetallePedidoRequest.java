package com.german.cabrera.sistema_pedidos.dto.pedido;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DetallePedidoRequest {

    @NotNull
    private Long productoId;

    @Min(1)
    private int cantidad;

    public DetallePedidoRequest(Long productoId, int cantidad) {
        this.productoId = productoId;
        this.cantidad = cantidad;
    }
}
