package com.adrianobarrosdev.model;

import java.util.Date;

public class Exames {

	private int id;
	private String tipo;
	private Date dataRealizacao;
	private String status;
	private String caminhoArquivo;
	
	public Exames() {
		
	}

	public Exames(int id, String tipo, Date dataRealizacao, String status, String caminhoArquivo) {
		this.id = id;
		this.tipo = tipo;
		this.dataRealizacao = dataRealizacao;
		this.status = status;
		this.caminhoArquivo = caminhoArquivo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCaminhoArquivo() {
		return caminhoArquivo;
	}

	public void setCaminhoArquivo(String caminhoArquivo) {
		this.caminhoArquivo = caminhoArquivo;
	}
	
}
