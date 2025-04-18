package com.german.cabrera.sistema_pedidos.builder;

import com.german.cabrera.sistema_pedidos.model.Producto;
import io.github.ger1211.builder.builder.AbstractPersistenceBuilder;

import java.math.BigDecimal;

public class ProductoBuilder extends AbstractPersistenceBuilder<Producto> {

    private ProductoBuilder() {
        this.instance = new Producto();
        this.instance.setNombre("Nombre");
        this.instance.setDescripcion("Descripción");
        this.instance.setPrecio(BigDecimal.ONE);
        this.instance.setStock(1);
    }

    public static ProductoBuilder basic() {
        return new ProductoBuilder();
    }

    public ProductoBuilder conNombre(String nombre) {
        this.instance.setNombre(nombre);
        return this;
    }

    public ProductoBuilder conDescripcion(String descripcion) {
        this.instance.setDescripcion(descripcion);
        return this;
    }

    public ProductoBuilder conPrecio(BigDecimal precio) {
        this.instance.setPrecio(precio);
        return this;
    }

    public ProductoBuilder conStock(int stock) {
        this.instance.setStock(stock);
        return this;
    }
}
