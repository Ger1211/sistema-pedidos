package com.german.cabrera.sistema_pedidos.integracion.service;

import com.german.cabrera.sistema_pedidos.builder.ProductoBuilder;
import com.german.cabrera.sistema_pedidos.builder.UsuarioBuilder;
import com.german.cabrera.sistema_pedidos.dto.pedido.DetallePedidoRequest;
import com.german.cabrera.sistema_pedidos.dto.pedido.PedidoRequest;
import com.german.cabrera.sistema_pedidos.dto.pedido.PedidoResponse;
import com.german.cabrera.sistema_pedidos.model.Pedido;
import com.german.cabrera.sistema_pedidos.model.PedidoDetalle;
import com.german.cabrera.sistema_pedidos.model.Producto;
import com.german.cabrera.sistema_pedidos.model.Usuario;
import com.german.cabrera.sistema_pedidos.service.PedidoService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PedidoServiceTests extends IntegrationTests {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PedidoService pedidoService;

    @Test
    void crearPedido_conPedidoRequest_creaPedido() {
        Usuario cliente = UsuarioBuilder.cliente().build(entityManager);
        Producto producto1 = ProductoBuilder.basic().conPrecio(BigDecimal.TEN).build(entityManager);
        Producto producto2 = ProductoBuilder.basic().conPrecio(BigDecimal.valueOf(20L)).build(entityManager);

        List<DetallePedidoRequest> detalles = List.of(
                new DetallePedidoRequest(producto1.getId(), 2),
                new DetallePedidoRequest(producto2.getId(), 1)
        );

        PedidoRequest request = new PedidoRequest(cliente.getId(), detalles);

        PedidoResponse pedido = pedidoService.crear(request);

        assertNotNull(pedido);
        assertNotNull(pedido.getId());
        assertEquals(cliente.getId(), pedido.getClienteId());
        assertEquals(2, pedido.getDetalles().size());
        assertEquals(0, pedido.getTotal().compareTo(new BigDecimal("40.00")));
    }

    @Test
    void crearPedido_conProductoInexistente_lanzaExcepcion() {
        Usuario cliente = UsuarioBuilder.cliente().build(entityManager);

        List<DetallePedidoRequest> detalles = List.of(
                new DetallePedidoRequest(-1L, 2)
        );

        PedidoRequest request = new PedidoRequest(cliente.getId(), detalles);

        assertThrows(EntityNotFoundException.class, () -> pedidoService.crear(request),
                "El producto no existe");
    }

    @Test
    void crearPedido_conClienteInexistente_lanzaExcepcion() {
        Producto producto1 = ProductoBuilder.basic().conPrecio(BigDecimal.TEN).build(entityManager);

        List<DetallePedidoRequest> detalles = List.of(
                new DetallePedidoRequest(producto1.getId(), 2)
        );

        PedidoRequest request = new PedidoRequest(-1L, detalles);

        assertThrows(EntityNotFoundException.class, () -> pedidoService.crear(request),
                "El usuario no existe");
    }

    @Test
    void crearPedido_conUsuarioNoClienteOAdmin_lanzaExcepcion() {
        Producto producto1 = ProductoBuilder.basic().conPrecio(BigDecimal.TEN).build(entityManager);
        Usuario cocina = UsuarioBuilder.cocina().build(entityManager);


        List<DetallePedidoRequest> detalles = List.of(
                new DetallePedidoRequest(producto1.getId(), 2)
        );

        PedidoRequest request = new PedidoRequest(cocina.getId(), detalles);

        assertThrows(IllegalArgumentException.class, () -> pedidoService.crear(request),
                "El usuario no es un cliente o admin");
    }

    @Test
    void obtenerPorCliente_conPedidoExistenteDeCliente_retornaPedido() {
        Usuario cliente = UsuarioBuilder.cliente().build(entityManager);
        Producto producto = ProductoBuilder.basic().build(entityManager);
        Pedido pedido = Pedido.builder()
                .fecha(LocalDateTime.now())
                .cliente(cliente)
                .total(producto.getPrecio())
                .detalles(List.of())
                .build();

        PedidoDetalle detalle = PedidoDetalle.builder()
                .cantidad(1)
                .producto(producto)
                .pedido(pedido)
                .build();

        pedido.setDetalles(List.of(detalle));

        entityManager.persist(pedido);

        List<PedidoResponse> pedidos = pedidoService.obtenerPorCliente(cliente.getId());

        assertNotNull(pedidos);
        assertEquals(1, pedidos.size());
        assertEquals(pedido.getId(), pedidos.get(0).getId());
    }

    @Test
    void obtenerPorId_conPedidoExistente_retornaPedido() {
        Usuario cliente = UsuarioBuilder.cliente().build(entityManager);
        Producto producto = ProductoBuilder.basic().build(entityManager);
        Pedido pedido = Pedido.builder()
                .fecha(LocalDateTime.now())
                .cliente(cliente)
                .total(producto.getPrecio())
                .detalles(List.of())
                .build();

        PedidoDetalle detalle = PedidoDetalle.builder()
                .cantidad(1)
                .producto(producto)
                .pedido(pedido)
                .build();

        pedido.setDetalles(List.of(detalle));

        entityManager.persist(pedido);

        PedidoResponse pedidoObtenido = pedidoService.obtenerPorId(pedido.getId());

        assertEquals(pedido.getId(), pedidoObtenido.getId());
        assertEquals(1, pedido.getDetalles().size());
    }

    @Test
    void obtenerPorId_conPedidoInexistente_lanzaExcepcion() {
        assertThrows(EntityNotFoundException.class, () -> pedidoService.obtenerPorId(-1L),
                "El pedido no existe");
    }

}
