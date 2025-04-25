package com.german.cabrera.sistema_pedidos.dto.pedido;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DetallePedidoResponse {
    private String productoNombre;
    private int cantidad;
    private BigDecimal precio;

    // Constructor
    public DetallePedidoResponse(String productoNombre, int cantidad, BigDecimal precio) {
        this.productoNombre = productoNombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }
}
