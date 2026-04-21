package com.gestao.service;

import com.gestao.dto.TarefaCreateDTO;
import com.gestao.dto.TarefaResponseDTO;
import com.gestao.exception.RegraNegocioException;
import com.gestao.model.*;
import com.gestao.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public TarefaResponseDTO criar(TarefaCreateDTO dto) {

        Projeto projeto = projetoRepository.findById(dto.getProjetoId())
                .orElseThrow(() -> new RegraNegocioException("Projeto não encontrado"));

        Usuario usuario = usuarioRepository.findById(dto.getResponsavelId())
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));

        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setPrioridade(dto.getPrioridade());
        tarefa.setProjeto(projeto);
        tarefa.setResponsavel(usuario);
        tarefa.setStatus(StatusTarefa.PENDENTE);

        tarefa = tarefaRepository.save(tarefa);

        TarefaResponseDTO response = new TarefaResponseDTO();
        response.setId(tarefa.getId());
        response.setTitulo(tarefa.getTitulo());
        response.setStatus(tarefa.getStatus());

        return response;
    }
}