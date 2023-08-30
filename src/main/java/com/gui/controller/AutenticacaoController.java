package com.gui.controller;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.gui.domain.dto.DadosToken;
import com.gui.domain.dto.DadosUsuario;
import com.gui.domain.model.Role;
import com.gui.domain.model.Usuario;
import com.gui.domain.service.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosToken> efetuarLogin(@RequestBody @Valid DadosUsuario dadosUsuario) {
        Collection<? extends GrantedAuthority> authorities = Set.of(Role.valueOf(dadosUsuario.roles())).stream()
            .map(role -> new SimpleGrantedAuthority(role.name()))
            .collect(Collectors.toSet());
        var authenticationToken = new UsernamePasswordAuthenticationToken(dadosUsuario.nome(), dadosUsuario.senha(), authorities);
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosToken(tokenJWT));
    }
}