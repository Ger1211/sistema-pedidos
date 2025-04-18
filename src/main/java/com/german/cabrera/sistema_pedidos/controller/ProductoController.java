package com.german.cabrera.sistema_pedidos.controller;

import com.german.cabrera.sistema_pedidos.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
