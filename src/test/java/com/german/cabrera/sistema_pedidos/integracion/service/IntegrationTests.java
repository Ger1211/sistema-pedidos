package com.german.cabrera.sistema_pedidos.integracion.service;

import com.german.cabrera.sistema_pedidos.SistemaPedidosApplicationTests;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@ActiveProfiles("test")
public abstract class IntegrationTests extends SistemaPedidosApplicationTests { }
