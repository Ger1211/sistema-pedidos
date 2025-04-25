package com.german.cabrera.sistema_pedidos.service;

import com.german.cabrera.sistema_pedidos.dto.pedido.DetallePedidoRequest;
import com.german.cabrera.sistema_pedidos.dto.pedido.DetallePedidoResponse;
import com.german.cabrera.sistema_pedidos.dto.pedido.PedidoRequest;
import com.german.cabrera.sistema_pedidos.dto.pedido.PedidoResponse;
import com.german.cabrera.sistema_pedidos.model.Pedido;
import com.german.cabrera.sistema_pedidos.model.PedidoDetalle;
import com.german.cabrera.sistema_pedidos.model.Producto;
import com.german.cabrera.sistema_pedidos.model.Usuario;
import com.german.cabrera.sistema_pedidos.repository.PedidoRepository;
import com.german.cabrera.sistema_pedidos.repository.ProductoRepository;
import com.german.cabrera.sistema_pedidos.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final UsuarioRepository usuarioRepository;
    private final ProductoRepository productoRepository;
    private final PedidoRepository pedidoRepository;

    public PedidoResponse crear(PedidoRequest pedidoRequest) {
        Usuario cliente = obtenerCliente(pedidoRequest.getClienteId());
        validar(cliente);
        Pedido pedido = new Pedido(cliente);

        List<PedidoDetalle> detalles = crearDetalles(pedidoRequest.getDetalles(), pedido);
        BigDecimal total = calcularTotal(detalles);

        pedido.setDetalles(detalles);
        pedido.setTotal(total);

        Pedido pedidoGuardado = pedidoRepository.save(pedido);
        return obtenerPedidoResponse(pedidoGuardado);
    }

    private Usuario obtenerCliente(Long clienteId) {
        return usuarioRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("El usuario no existe"));
    }

    private void validar(Usuario usuario) {
        Assert.isTrue(usuario.getRoles().stream().anyMatch(rol -> ("CLIENTE".equals(rol)  || "ADMIN".equals(rol) )), "El usuario no es un cliente o admin");
    }

    private List<PedidoDetalle> crearDetalles(List<DetallePedidoRequest> detallesRequest, Pedido pedido) {
        return detallesRequest.stream()
                .map(detalleRequest -> {
                    Producto producto = productoRepository.findById(detalleRequest.getProductoId())
                            .orElseThrow(() -> new EntityNotFoundException("El producto no existe"));
                    return new PedidoDetalle(detalleRequest, pedido, producto);
                })
                .toList();
    }

    private BigDecimal calcularTotal(List<PedidoDetalle> detalles) {
        return detalles.stream()
                .map(detalle -> detalle.getProducto().getPrecio()
                        .multiply(BigDecimal.valueOf(detalle.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private PedidoResponse obtenerPedidoResponse(Pedido pedido) {
        List<DetallePedidoResponse> detallesResponse = pedido.getDetalles().stream()
                .map(detalle -> new DetallePedidoResponse(
                        detalle.getProducto().getNombre(),
                        detalle.getCantidad(),
                        detalle.getProducto().getPrecio()
                ))
                .collect(Collectors.toList());

        return new PedidoResponse(
                pedido.getId(),
                pedido.getCliente().getId(),
                detallesResponse,
                pedido.getTotal());
    }

}
