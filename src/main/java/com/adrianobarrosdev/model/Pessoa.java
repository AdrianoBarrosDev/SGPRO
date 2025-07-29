package com.adrianobarrosdev.model;

import java.util.Date;

public class Pessoa {

	private int id;
	private String nome;
	private String email;
	private String cpf;
	private String telefone;
	private Date dataNascimento;
	
	public Pessoa() {
		
	}
	
	public Pessoa(int id) {
		this.id = id;
	}

	public Pessoa(int id, String nome, String email, String cpf, String telefone, Date dataNascimento) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
	}
	
	public Pessoa(String nome, String email, String cpf, String telefone, Date dataNascimento) {
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
}
