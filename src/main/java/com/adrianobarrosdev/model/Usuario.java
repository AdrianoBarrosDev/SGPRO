package com.adrianobarrosdev.model;

public class Usuario {
	
	private int id;
	private String senha;
	private Pessoa pessoa;
	private Empresa empresa;
	
	public Usuario() {
		
	}

	public Usuario(int id, String senha, Pessoa pessoa, Empresa empresa) {
		this.id = id;
		this.senha = senha;
		this.pessoa = pessoa;
		this.empresa = empresa;
	}

	public Usuario(String senha, Pessoa pessoa, Empresa empresa) {
		this.senha = senha;
		this.pessoa = pessoa;
		this.empresa = empresa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
}
