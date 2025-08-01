package com.adrianobarrosdev.model;

public class Setor {

	private int id;
	private String nome;
	private String localizacao;
	private String descricao;
	private int examesPendentes;
	private int quantidadeColaboradores = 0;
	
	public Setor() {
		
	}

	public Setor(int id, String nome, String localizacao, String descricao) {
		this.id = id;
		this.nome = nome;
		this.localizacao = localizacao;
		this.descricao = descricao;
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

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getExamesPendentes() {
		return examesPendentes;
	}

	public void setExamesPendentes(int examesPendentes) {
		this.examesPendentes = examesPendentes;
	}

	public int getQuantidadeColaboradores() {
		return quantidadeColaboradores;
	}

	public void setQuantidadeColaboradores(int quantidadeColaboradores) {
		this.quantidadeColaboradores = quantidadeColaboradores;
	}
	
}
