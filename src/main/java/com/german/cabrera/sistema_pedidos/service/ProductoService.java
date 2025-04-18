package com.german.cabrera.sistema_pedidos.service;

import com.german.cabrera.sistema_pedidos.dto.producto.ProductoRequest;
import com.german.cabrera.sistema_pedidos.model.Producto;
import com.german.cabrera.sistema_pedidos.repository.ProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public Producto crearOActualizar(ProductoRequest producto) {
        Producto productoACrearOActualizar = new Producto(producto);
        return productoRepository.save(productoACrearOActualizar);
    }
}
