package com.german.cabrera.sistema_pedidos.integracion.service;

import com.german.cabrera.sistema_pedidos.builder.ProductoBuilder;
import com.german.cabrera.sistema_pedidos.dto.producto.ProductoRequest;
import com.german.cabrera.sistema_pedidos.model.Producto;
import com.german.cabrera.sistema_pedidos.service.ProductoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductoServiceTests extends IntegrationTests {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ProductoService productoService;

    @Test
    void obtenerTodos_conProductoExistente_retornaListaConEseProducto() {
        Producto producto = ProductoBuilder.basic().build(entityManager);

        List<Producto> productos = productoService.obtenerTodos();

        assertNotNull(productos);
        assertEquals(1, productos.size());
        assertEquals(productos.get(0).getId(), producto.getId());
    }

    @Test
    void obtenerProducto_conProductoExistente_retornaProducto() {
        Producto producto = ProductoBuilder.basic().build(entityManager);

        Producto productoGuardado = productoService.obtenerProducto(producto.getId());

        assertNotNull(producto);
        assertEquals(producto.getId(), productoGuardado.getId());
    }

    @Test
    void obtenerProducto_conProductoInexistente_lanzaExcepcion() {
        Long productoInexistente = -1L;

        String message = assertThrows(EntityNotFoundException.class, () -> productoService.obtenerProducto(productoInexistente)).getMessage();
        assertEquals("Producto no encontrado", message);
    }

    @Test
    void crear_conProductoValido_retornaProductoCreado() {
        ProductoRequest producto = ProductoRequest.builder()
                .nombre("Arroz")
                .descripcion("Arroz blanco grano fino")
                .precio(BigDecimal.TEN)
                .stock(0)
                .build();

        Producto productoCreado = productoService.crear(producto);

        assertNotNull(productoCreado);
        assertNotNull(productoCreado.getId());
    }
}
