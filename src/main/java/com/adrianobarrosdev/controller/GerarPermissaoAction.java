package com.adrianobarrosdev.controller;

import com.adrianobarrosdev.service.PermissaoService;

public class GerarPermissaoAction {
	
	private PermissaoService permissaoService = new PermissaoService();
	private int empresaIdPermissao;
	private String cpfColaboradorPermissao;
	
	public int getEmpresaIdPermissao() {
		return empresaIdPermissao;
	}

	public void setEmpresaIdPermissao(int empresaIdPermissao) {
		this.empresaIdPermissao = empresaIdPermissao;
	}

	public String getCpfColaboradorPermissao() {
		return cpfColaboradorPermissao;
	}

	public void setCpfColaboradorPermissao(String cpfColaboradorPermissao) {
		this.cpfColaboradorPermissao = cpfColaboradorPermissao;
	}

	public String execute() {
		permissaoService.gerarPermissao(cpfColaboradorPermissao, empresaIdPermissao);
		return "success";
	}
	
}
