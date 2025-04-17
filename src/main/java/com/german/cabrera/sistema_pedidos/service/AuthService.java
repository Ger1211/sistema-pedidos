package com.german.cabrera.sistema_pedidos.service;

import com.german.cabrera.sistema_pedidos.dto.auth.AuthRequest;
import com.german.cabrera.sistema_pedidos.dto.auth.AuthResponse;
import com.german.cabrera.sistema_pedidos.model.Usuario;
import com.german.cabrera.sistema_pedidos.repository.UsuarioRepository;
import com.german.cabrera.sistema_pedidos.security.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword())
        );

        Usuario usuario = usuarioRepository.findByEmail(authRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        String token = jwtService.generarToken(usuario);

        return new AuthResponse(token);
    }
}
