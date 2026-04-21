package com.gestao.dto;

import com.gestao.model.StatusTarefa;

public class TarefaResponseDTO {

    private Long id;
    private String titulo;
    private StatusTarefa status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public StatusTarefa getStatus() { return status; }
    public void setStatus(StatusTarefa status) { this.status = status; }
}