package com.adrianobarrosdev.model;

public class Empresa {
	
	private int id;
	private String nome;
	private String cnpj;
	private String emailContato;
	private String telefone;
	private String chaveAdmin;
	private String chaveGestor;
	
	public Empresa() {
		
	}
	
	public Empresa(int id) {
		this.id = id;
	}

	public Empresa(int id, String nome, String cnpj, String emailContato, String telefone, String chaveAdmin,
			String chaveGestor) {
		this.id = id;
		this.nome = nome;
		this.cnpj = cnpj;
		this.emailContato = emailContato;
		this.telefone = telefone;
		this.chaveAdmin = chaveAdmin;
		this.chaveGestor = chaveGestor;
	}
	
	public Empresa(String nome, String cnpj, String emailContato, String telefone, String chaveAdmin, String chaveGestor) {
		this.nome = nome;
		this.cnpj = cnpj;
		this.emailContato = emailContato;
		this.telefone = telefone;
		this.chaveAdmin = chaveAdmin;
		this.chaveGestor = chaveGestor;
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

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmailContato() {
		return emailContato;
	}

	public void setEmailContato(String emailContato) {
		this.emailContato = emailContato;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getChaveAdmin() {
		return chaveAdmin;
	}

	public void setChaveAdmin(String chaveAdmin) {
		this.chaveAdmin = chaveAdmin;
	}

	public String getChaveGestor() {
		return chaveGestor;
	}

	public void setChaveGestor(String chaveGestor) {
		this.chaveGestor = chaveGestor;
	}
	
}
