package com.german.cabrera.sistema_pedidos.integracion.service;

import com.german.cabrera.sistema_pedidos.builder.ProductoBuilder;
import com.german.cabrera.sistema_pedidos.dto.producto.ProductoRequest;
import com.german.cabrera.sistema_pedidos.model.Producto;
import com.german.cabrera.sistema_pedidos.repository.ProductoRepository;
import com.german.cabrera.sistema_pedidos.service.ProductoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ProductoServiceTests extends IntegrationTests {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private ProductoRepository productoRepository;

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
    void crear_conProductoValidoInexistente_retornaProductoCreado() {
        ProductoRequest producto = ProductoRequest.builder()
                .nombre("Arroz")
                .descripcion("Arroz blanco grano fino")
                .precio(BigDecimal.TEN)
                .stock(0)
                .build();

        Producto productoCreado = productoService.crear(producto);

        assertNull(producto.getId());
        assertNotNull(productoCreado);
        assertNotNull(productoCreado.getId());
    }

    @Test
    void crear_conProductoConId_lanzaExcepcion() {
        ProductoRequest producto = ProductoRequest.builder()
                .id(1L)
                .nombre("Arroz")
                .descripcion("Arroz blanco grano fino")
                .precio(BigDecimal.TEN)
                .stock(0)
                .build();

        String message = assertThrows(IllegalArgumentException.class, () -> productoService.crear(producto)).getMessage();
        assertEquals("El producto a crear no puede tener id", message);
    }

    @Test
    void actualizar_conProductoValidoExistente_retornaProductoActualizado() {
        String nuevoNombre = "Arroz Grano Fino";

        Producto productoExistente = ProductoBuilder.basic()
                .conNombre("Arroz")
                .conDescripcion("Arroz blanco grano fino")
                .conPrecio(BigDecimal.TEN)
                .conStock(1)
                .build(entityManager);

        ProductoRequest producto = ProductoRequest.builder()
                .id(productoExistente.getId())
                .nombre(nuevoNombre)
                .descripcion(productoExistente.getDescripcion())
                .precio(productoExistente.getPrecio())
                .stock(productoExistente.getStock())
                .build();

        Producto productoActualizado = productoService.actualizar(producto);

        assertNotNull(productoActualizado);
        assertNotNull(productoActualizado.getId());
        assertEquals(productoExistente.getId(), productoActualizado.getId());
        assertEquals(nuevoNombre, productoActualizado.getNombre());
    }

    @Test
    void actualizar_conProductoSinId_lanzaExcepcion() {
        ProductoRequest producto = ProductoRequest.builder()
                .id(null)
                .nombre("Arroz")
                .descripcion("Arroz blanco grano fino")
                .precio(BigDecimal.TEN)
                .stock(0)
                .build();

        String message = assertThrows(IllegalArgumentException.class, () -> productoService.actualizar(producto)).getMessage();
        assertEquals("El producto a actualizar debe tener id", message);
    }

    @Test
    void eliminar_conProductoExistente_eliminaProducto() {
        Producto producto = ProductoBuilder.basic().build(entityManager);
        Long id = producto.getId();
        assertNotNull(id);

        productoService.eliminar(id);

        Optional<Producto> productoEliminado = productoRepository.findById(id);
        assertTrue(productoEliminado.isEmpty());
    }
}
