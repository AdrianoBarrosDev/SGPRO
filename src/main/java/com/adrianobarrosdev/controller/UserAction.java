package com.adrianobarrosdev.controller;

import java.util.Date;

import com.adrianobarrosdev.model.Pessoa;
import com.adrianobarrosdev.model.Usuario;
import com.adrianobarrosdev.service.UsuarioService;

public class UserAction {

	private UsuarioService usuarioService = new UsuarioService();
	
	private String cpf;
	private String nome;
	private String email;
	private String telefone;
	private Date dataNascimento;
	private String senha;
	private String confirmarSenha;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}
	
	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public String create() {
		usuarioService.cadastrar(new Usuario(senha, new Pessoa(nome, email, cpf, telefone, dataNascimento), null));
		return "success";
	}
	
	
}
