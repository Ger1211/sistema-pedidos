package com.german.cabrera.sistema_pedidos.service;

import com.german.cabrera.sistema_pedidos.dto.producto.ProductoRequest;
import com.german.cabrera.sistema_pedidos.model.Producto;
import com.german.cabrera.sistema_pedidos.repository.ProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public Producto obtenerProducto(Long productoId) {
        return productoRepository.findById(productoId)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
    }

    public Producto crear(ProductoRequest producto) {
        Assert.isNull(producto.getId(), "El producto a crear no puede tener id");
        return crearOActualizar(producto);
    }

    public Producto actualizar(ProductoRequest producto) {
        Assert.notNull(producto.getId(), "El producto a actualizar debe tener id");
        return crearOActualizar(producto);
    }

    private Producto crearOActualizar(ProductoRequest producto) {
        Producto productoACrearOActualizar = new Producto(producto);
        return productoRepository.save(productoACrearOActualizar);
    }
}
