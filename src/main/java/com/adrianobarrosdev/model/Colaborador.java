package com.adrianobarrosdev.model;

import java.util.Date;
import java.util.List;

public class Colaborador {

	private int id;
	private Pessoa pessoa;
	private String matricula;
	private String cargo;
	private Date dataAdmissao;
	private Setor setor;
	private String status;
	private String indicadorRisco;
	private List<Exames> listaExames;
	private List<Exames> listaExamesPendentes;
	
	public Colaborador() {
		
	}
	
	public Colaborador(int id, Pessoa pessoa, String matricula, String cargo, Date dataAdmissao, Setor setor, String status, String indicadorRisco, List<Exames> listaExames) {
		this.id = id;
		this.pessoa = pessoa;
		this.matricula = matricula;
		this.cargo = cargo;
		this.dataAdmissao = dataAdmissao;
		this.setor = setor;
		this.status = status;
		this.indicadorRisco = indicadorRisco;
		this.listaExames = listaExames;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Date getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIndicadorRisco() {
		return indicadorRisco;
	}

	public void setIndicadorRisco(String indicadorRisco) {
		this.indicadorRisco = indicadorRisco;
	}

	public List<Exames> getListaExames() {
		return listaExames;
	}

	public void setListaExames(List<Exames> listaExames) {
		this.listaExames = listaExames;
	}

	public List<Exames> getListaExamesPendentes() {
		return listaExamesPendentes;
	}

	public void setListaExamesPendentes(List<Exames> listaExamesPendentes) {
		this.listaExamesPendentes = listaExamesPendentes;
	}
	
}
