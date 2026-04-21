package com.gestao.dto;

import com.gestao.model.StatusProjeto;
import java.time.LocalDate;

public class ProjetoResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFimPrevista;
    private StatusProjeto status;

    private Long gerenteId;
    private String gerenteNome;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public LocalDate getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}
	public LocalDate getDataFimPrevista() {
		return dataFimPrevista;
	}
	public void setDataFimPrevista(LocalDate dataFimPrevista) {
		this.dataFimPrevista = dataFimPrevista;
	}
	public StatusProjeto getStatus() {
		return status;
	}
	public void setStatus(StatusProjeto status) {
		this.status = status;
	}
	public Long getGerenteId() {
		return gerenteId;
	}
	public void setGerenteId(Long gerenteId) {
		this.gerenteId = gerenteId;
	}
	public String getGerenteNome() {
		return gerenteNome;
	}
	public void setGerenteNome(String gerenteNome) {
		this.gerenteNome = gerenteNome;
	}

    
    
}
