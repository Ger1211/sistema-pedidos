package com.german.cabrera.sistema_pedidos.builder;

import com.german.cabrera.sistema_pedidos.model.Usuario;
import io.github.ger1211.builder.builder.AbstractPersistenceBuilder;

import java.util.Set;

public class UsuarioBuilder extends AbstractPersistenceBuilder<Usuario> {

    private UsuarioBuilder() {
        this.instance = new Usuario();
        this.instance.setRoles(Set.of("CLIENTE"));
        this.instance.setEmail("cliente@email.com");
        this.instance.setPassword("password");

    }

    public static UsuarioBuilder cliente() {
        return new UsuarioBuilder();
    }

    public static UsuarioBuilder cocina() {
        UsuarioBuilder usuarioBuilder = new UsuarioBuilder();
        usuarioBuilder.instance.setRoles(Set.of("COCINA"));
        return usuarioBuilder;
    }
}
