package com.german.cabrera.sistema_pedidos.model;

import com.german.cabrera.sistema_pedidos.dto.producto.ProductoRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private int stock;

    public Producto(ProductoRequest productoRequest) {
        this.id = productoRequest.getId();
        this.nombre = productoRequest.getNombre();
        this.descripcion = productoRequest.getDescripcion();
        this.precio = productoRequest.getPrecio();
        this.stock = productoRequest.getStock();
    }
}

