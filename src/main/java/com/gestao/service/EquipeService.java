package com.gestao.service;

import com.gestao.dto.EquipeCreateDTO;
import com.gestao.dto.EquipeResponseDTO;
import com.gestao.model.Equipe;
import com.gestao.repository.EquipeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipeService {

    @Autowired
    private EquipeRepository equipeRepository;

    public EquipeResponseDTO criar(EquipeCreateDTO dto) {

        Equipe equipe = new Equipe();
        equipe.setNome(dto.getNome());
        equipe.setDescricao(dto.getDescricao());

        equipe = equipeRepository.save(equipe);

        return toDTO(equipe);
    }

    private EquipeResponseDTO toDTO(Equipe equipe) {
        EquipeResponseDTO dto = new EquipeResponseDTO();
        dto.setId(equipe.getId());
        dto.setNome(equipe.getNome());
        dto.setDescricao(equipe.getDescricao());
        return dto;
    }
}