package com.adrianobarrosdev.model;

import java.util.Date;

public class ExamesPendentes {

	private String nomeSetor;
	private String nomeColaborador;
	private String tipoExame;
	private Date dataPrevista;
	
	public ExamesPendentes() {
		
	}

	public ExamesPendentes(String nomeSetor, String nomeColaborador, String tipoExame, Date dataPrevista) {
		this.nomeSetor = nomeSetor;
		this.nomeColaborador = nomeColaborador;
		this.tipoExame = tipoExame;
		this.dataPrevista = dataPrevista;
	}

	public String getNomeSetor() {
		return nomeSetor;
	}

	public void setNomeSetor(String nomeSetor) {
		this.nomeSetor = nomeSetor;
	}

	public String getNomeColaborador() {
		return nomeColaborador;
	}

	public void setNomeColaborador(String nomeColaborador) {
		this.nomeColaborador = nomeColaborador;
	}

	public String getTipoExame() {
		return tipoExame;
	}

	public void setTipoExame(String tipoExame) {
		this.tipoExame = tipoExame;
	}

	public Date getDataPrevista() {
		return dataPrevista;
	}

	public void setDataPrevista(Date dataPrevista) {
		this.dataPrevista = dataPrevista;
	}
	
}
