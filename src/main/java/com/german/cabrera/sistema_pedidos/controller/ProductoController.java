package com.german.cabrera.sistema_pedidos.controller;

import com.german.cabrera.sistema_pedidos.dto.producto.ProductoRequest;
import com.german.cabrera.sistema_pedidos.service.ProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @GetMapping
    public ResponseEntity<?> obtener(@RequestParam(required = false) Long productoId) {
        if (productoId != null) {
            return ResponseEntity.ok(productoService.obtenerProducto(productoId));
        }
        return ResponseEntity.ok(productoService.obtenerTodos());
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody ProductoRequest producto) {
        return ResponseEntity.ok(productoService.crear(producto));
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@Valid @RequestBody ProductoRequest producto) {
        return ResponseEntity.ok(productoService.actualizar(producto));
    }

    @DeleteMapping
    public ResponseEntity<?> eliminar(@RequestParam long productoId) {
        productoService.eliminar(productoId);
        return ResponseEntity.noContent().build();
    }
}
