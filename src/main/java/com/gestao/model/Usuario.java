package com.gestao.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "Usuários")

public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "O nome é Obrigatório!!")
	private String nome;

	@Column(unique = true, nullable = false)
	private String cpf;

	@Email(message = "Email Inválido!!")
	@Column(unique = true, nullable = false)
	private String email;

	@NotBlank(message = "O cargo é Obrigatório!!")
	private String cargo;

	@Column(unique = true, nullable = false)
	private String login;

	@NotBlank(message = "A senha é Obrigatória!!")
	private String senha;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Perfil perfil;

	@ManyToOne
	@JoinColumn(name = "equipe_id")
	private Equipe equipe;

	public Usuario() {
	}

	public Usuario(long id , String nome,String cpf, String email, String cargo,String login,
			 String senha, Perfil perfil, Equipe equipe ) {
		
	
		 this.id = id;
		 this.nome = nome;
		 this.cpf = cpf;
		 this.email = email;
		 this.cargo = cargo;
		 this.login = login;
		 this.senha = senha;
		 this.perfil = perfil;
		 this.equipe = equipe;
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}
}
