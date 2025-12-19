package com.gui.controller;

import com.gui.domain.dto.DadosTokenDTO;
import com.gui.domain.dto.DadosUsuarioDTO;
import com.gui.domain.model.Usuario;
import com.gui.domain.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DadosTokenDTO> efetuarLogin(@RequestBody @Valid DadosUsuarioDTO dadosUsuario) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dadosUsuario.nome(), dadosUsuario.senha());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenDTO(tokenJWT));
    }
}