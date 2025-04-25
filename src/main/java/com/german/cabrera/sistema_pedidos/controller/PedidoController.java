package com.german.cabrera.sistema_pedidos.controller;

import com.german.cabrera.sistema_pedidos.dto.pedido.PedidoRequest;
import com.german.cabrera.sistema_pedidos.service.PedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody PedidoRequest pedidoRequest) {
        return new ResponseEntity<>(pedidoService.crear(pedidoRequest), HttpStatus.CREATED);
    }

    @GetMapping("/clientes/{clienteId}")
    public ResponseEntity<?> obtenerPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(pedidoService.obtenerPorCliente(clienteId));
    }

    @GetMapping("/{pedidoId}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long pedidoId) {
        return ResponseEntity.ok(pedidoService.obtenerPorId(pedidoId));
    }
}
