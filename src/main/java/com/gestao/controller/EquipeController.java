package com.gestao.controller;

import com.gestao.dto.EquipeCreateDTO;
import com.gestao.dto.EquipeResponseDTO;
import com.gestao.service.EquipeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/equipes")
public class EquipeController {

    @Autowired
    private EquipeService equipeService;

    @PostMapping
    public ResponseEntity<EquipeResponseDTO> criar(@RequestBody EquipeCreateDTO dto) {
        return ResponseEntity.status(201).body(equipeService.criar(dto));
    }
}