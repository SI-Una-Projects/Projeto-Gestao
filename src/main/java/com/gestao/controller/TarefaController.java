package com.gestao.controller;

import com.gestao.dto.TarefaCreateDTO;
import com.gestao.dto.TarefaResponseDTO;
import com.gestao.service.TarefaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaResponseDTO> criar(@RequestBody TarefaCreateDTO dto) {
        return ResponseEntity.status(201).body(tarefaService.criar(dto));
    }
}