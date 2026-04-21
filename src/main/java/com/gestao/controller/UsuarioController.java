package com.gestao.controller;

import com.gestao.dto.UsuarioCreateDTO;
import com.gestao.dto.UsuarioResponseDTO;
import com.gestao.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criar(@RequestBody UsuarioCreateDTO dto) {
        UsuarioResponseDTO usuario = usuarioService.criar(dto);
        return ResponseEntity.ok(usuario);
    }

    
    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok("Endpoint de listagem ainda não implementado");
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok("Busca por ID ainda não implementada");
    }
}