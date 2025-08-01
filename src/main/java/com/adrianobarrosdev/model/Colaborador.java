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
	private List<Exames> listaExames;
	
	public Colaborador() {
		
	}
	
	public Colaborador(int id, Pessoa pessoa, String matricula, String cargo, Date dataAdmissao, Setor setor, List<Exames> listaExames) {
		this.id = id;
		this.pessoa = pessoa;
		this.matricula = matricula;
		this.cargo = cargo;
		this.dataAdmissao = dataAdmissao;
		this.setor = setor;
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

	public List<Exames> getListaExames() {
		return listaExames;
	}

	public void setListaExames(List<Exames> listaExames) {
		this.listaExames = listaExames;
	}
	
}
