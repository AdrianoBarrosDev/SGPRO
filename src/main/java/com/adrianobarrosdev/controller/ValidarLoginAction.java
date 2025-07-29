package com.adrianobarrosdev.controller;

import java.util.Map;
import java.util.Optional;

import com.adrianobarrosdev.model.Usuario;
import com.adrianobarrosdev.service.UsuarioService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ValidarLoginAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;

	private UsuarioService usuarioService = new UsuarioService();
	
	private String cpf;
	private String senha;
	private String empresaId;
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(String empresaId) {
		this.empresaId = empresaId;
	}

	public String execute() {
		
		if (cpf == null || cpf.isEmpty()) {
		    addFieldError("cpf", "CPF é obrigatório.");
		}

		if (senha == null || senha.isEmpty()) {
		    addFieldError("senha", "Senha é obrigatória.");
		}

		if (hasFieldErrors()) {
		    return INPUT;
		}
		
		if(empresaId == null || empresaId.isEmpty()) {
			Optional<Usuario> usuarioValidado = usuarioService.validarLogin(cpf, senha);
			if(usuarioValidado.isPresent()) {
				
				Map<String, Object> session = ActionContext.getContext().getSession();
				session.put("usuarioLogado", usuarioValidado.get());
				
				return "sistemaPessoa";
				
			} else {
				addActionError("CPF ou senha inválidos.");
				return INPUT;
			}
			
		} else {
			Optional<Usuario> usuarioValidado = usuarioService.validarUsuarioEmpresa(cpf, senha, empresaId);
			if(usuarioValidado.isPresent()) {
				
				Map<String, Object> session = ActionContext.getContext().getSession();
				session.put("usuarioLogado", usuarioValidado.get());
				
				return "sistemaEmpresa";
				
			} else {
				addActionError("CPF, senha ou ID Empresa inválidos.");
				return INPUT;
			}
		}
		
	}
	
	
	
}
