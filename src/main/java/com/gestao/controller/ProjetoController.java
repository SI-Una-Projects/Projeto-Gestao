package com.gestao.controller;

import com.gestao.dto.ProjetoCreateDTO;
import com.gestao.dto.ProjetoResponseDTO;
import com.gestao.service.ProjetoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    // ✅ Criar projeto
    @PostMapping
    public ResponseEntity<ProjetoResponseDTO> criar(@RequestBody ProjetoCreateDTO dto) {
        ProjetoResponseDTO projeto = projetoService.criar(dto);
        return ResponseEntity.status(201).body(projeto);
    }

    // 🔍 Listar (simples por enquanto)
    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok("Listagem ainda não implementada");
    }

    // 🔎 Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok("Busca por ID ainda não implementada");
    }
}