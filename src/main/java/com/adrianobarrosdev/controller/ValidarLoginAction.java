package com.adrianobarrosdev.controller;

import java.util.Map;
import java.util.Optional;

import org.apache.struts2.interceptor.SessionAware;

import com.adrianobarrosdev.model.Usuario;
import com.adrianobarrosdev.service.UsuarioService;
import com.opensymphony.xwork2.ActionSupport;

public class ValidarLoginAction extends ActionSupport implements SessionAware {
	
	private static final long serialVersionUID = 1L;

	private UsuarioService usuarioService = new UsuarioService();
	
	private Map<String, Object> session;
	private String cpfLogin;
	private String senhaLogin;
	private String empresaId;
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	
	public String getCpfLogin() {
		return cpfLogin;
	}
	
	public void setCpfLogin(String cpfLogin) {
		this.cpfLogin = cpfLogin;
	}
	
	public String getSenhaLogin() {
		return senhaLogin;
	}
	
	public void setSenhaLogin(String senhaLogin) {
		this.senhaLogin = senhaLogin;
	}
	
	public String getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(String empresaId) {
		this.empresaId = empresaId;
	}
	
	@Override
	public void validate() {
		if (cpfLogin == null || cpfLogin.trim().isEmpty()) {
	        addFieldError("cpfLogin", "CPF é obrigatório.");
	    }

	    if (senhaLogin == null || senhaLogin.trim().isEmpty()) {
	        addFieldError("senhaLogin", "Senha é obrigatória.");
	    }
	}

	@Override
	public String execute() {

		Optional<Usuario> usuarioValidado;
		
		if (empresaId == null || empresaId.isEmpty()) {
			usuarioValidado = usuarioService.validarLogin(cpfLogin, senhaLogin);
		} else {
			usuarioValidado = usuarioService.validarUsuarioEmpresa(cpfLogin, senhaLogin, empresaId);
		}
		
		if (usuarioValidado.isPresent()) {
			Usuario usuario = usuarioValidado.get();
			session.put("usuarioLogado", usuario);
			return SUCCESS;
			
		} else {
			addActionError("Credenciais inválidas.");
			return INPUT;
		}
		
	}
	
}
